# fabric-framework

<<<<<<< HEAD
### 介绍
主要包含fabric-security和fabric-gateway-plus两个框架，主要实现以下功能：

1. fabric-security：对读取当前登录用户信息，并根据当前登录用户信息（UserInfo接口）取得与Fabric交互所需的信息（User接口）的流程进行了封装。此外，这个框架还会将信息存入到一个ThreadLocal对象中，供全局使用；
2. fabric-gateway：
   1. 对取得Grpc连接、产生Gateway对象、调用链码的过程进行了封装。允许类似Mybatis调用sql语句一样地调用Fabric的链码；
   2. 对Grpc连接进行了池化，并且允许设置不同的节点连接级别（如全部使用单个peer节点，一个组织使用一个peer节点）
   3. 对提交链码的过程提供了一定的事务机制，如（直接异步提交、直接同步提交、异步等待整个事务最后提交、异步等待整个事务最后提交并用上锁中心化解决双花问题）



### 使用说明

#### fabric-security

1. 首先导入fabric-security-spring-boot-starter包

```xml
<dependency>
    <groupId>com.wisecoach</groupId>
    <artifactId>fabric-security-spring-boot-starter</artifactId>
    <version>${fabric-framework.version}</version>
</dependency>
```

2. 配置自定义的UserInfoProvider，这个是必须的，也是对外的接口，可以考虑从SpringSecurity中取得信息，也可以自己实现自己的UserInfo，来提供额外的信息

```java
public class CustomUserInfoProvider extends AbstractUserInfoProvider {
    @Override
    protected UserInfo fetchUserInfo(Object obj, Method method, Object[] args) {
        return new UserInfoImpl("admin", "adminpw");
    }
}
```

3. 配置自定义的UserProvider，可以在support方法中要求第二步中自定义的UserInfo，getPriority决定了provider被调用的顺序，越小越先执行。所有UserProvider只要注册到Spring容器中，就会被自动加入到provider队列中，按序被调用尝试取得user

```java
public class CustomUserProvider implements UserProvider {

    private final static String pvtPath = "/mnt/F/fabric-samples/fabric-samples-2.4.8/fabric-samples/test-network/" +
            "organizations/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp/keystore/priv_key";

    private final static String signerCert = "/mnt/F/fabric-samples/fabric-samples-2.4.8/fabric-samples/test-network" +
            "/organizations/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp/signcerts/cert.pem";

    @Override
    public User getUser(UserInfo userInfo) {
        Signer signer = null;
        X509Certificate certificate = null;
        try {
            BufferedReader privateKeyReader = Files.newBufferedReader(Paths.get(pvtPath), StandardCharsets.UTF_8);
            PrivateKey privateKey = Identities.readPrivateKey(privateKeyReader);
            signer = Signers.newPrivateKeySigner(privateKey);
            BufferedReader certReader = Files.newBufferedReader(Paths.get(signerCert), StandardCharsets.UTF_8);
            certificate = Identities.readX509Certificate(certReader);
        } catch (IOException | InvalidKeyException | CertificateException e) {
            throw new RuntimeException(e);
        }
        return new UserImpl("Org1MSP", signer, certificate);
    }

    @Override
    public boolean support(UserInfo userInfo) {
        return userInfo instanceof UserInfoImpl;
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
```

4. 给需要fabric交互的service和方法加上注解@FabricService和@FabricTransaction

```java
@FabricService
public class TestServiceImpl implements TestService {

    @Autowired
    private BasicContract basicContract;

    @Override
    @FabricTransaction
    @ChaincodeTransaction		// 这个是另一个框架的
    public String test() {
        UserContextHolder.getContext().getUser()		// 可以取得创建好的User对象
        basicContract.initLedger();
        AssetCO assetCO = basicContract.readAsset("asset1");
        return JSONUtils.serialize(assetCO);
    }
}
```



#### fabric-gateway-plus

1. 导入fabric-gateway-plus-spring-boot-starter

```xml
<dependency>
    <groupId>com.wisecoach</groupId>
    <artifactId>fabric-gateway-plus-spring-boot-starter</artifactId>
    <version>${fabric-framework.version}</version>
</dependency>
```

2. 进行一些基础的配置

```yaml
fabric:
  gatewayplus:
  	default-channel: mychannel		# 可以配置所有链码的默认channel
    conn:
      single-peer-info:				# 配置全部使用单个peer节点的端点、tls证书、authority等信息
        tls-cert-path: /mnt/F/fabric-samples/fabric-samples-2.4.8/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt
        endpoint: localhost:7051
        authority: peer0.org1.example.com
    pool:							# 配置是否使用grpc连接池，默认开启，还可以修改一些关于连接池的配置
      use-pool: true
```

3. 自己实现一个GatewayInfoProvider，从另一个框架里面拿出User就行了，GatewayInfo和User一样的，我只是为了将两个框架独立开来

```java
public class CustomGatewayInfoProvider extends AbstractGatewayInfoProvider {

    @Override
    protected GatewayInfo fetchGatewayInfo(Object obj, Method method, Object[] args) {
        User user = UserContextHolder.getContext().getUser();
        return new GatewayInfoImpl(user.getMspId(), user.getSigner(), user.getCertificate());
    }
}
```

4. 跟Mybatis一样创建一个接口，用来调链码

```java
@Repository
@ContractMapper("basic")			// 可以指定channelID、chaincodeName、contractName
public interface BasicContract {
    @Evalute("ReadAsset")			// @Evaluate 只是找一个节点背书一下得到链码执行结果的调用方式，里面写TransactionName
    AssetCO readAsset(String assetId);

    @Submit("InitLedger")			// @Submit 背书提交给事务
    void initLedger();
}
```

5. 给Service方法几个注解

```java
@FabricTransaction
@ChaincodeTransaction(TransactionStrategy.SUBMIT_ASYNC)		// 加上这个注解，强化方法才会生效。还可以设置事务策略，表示这个service方法
															// 会采用什么策略，详细看TransactionStrategy上的注释
public String test() {
    basicContract.initLedger();
    AssetCO assetCO = basicContract.readAsset("asset1");
    return JSONUtils.serialize(assetCO);
}
```



### 原理介绍

#### fabric-security

核心就是通过AOP将加强方法切入到被FabricService注解的类和FabricTransaction注解的方法，加强方法大致如下：

1. 判断注解，是否需要加强
2. 取得UserInfo
3. 根据UserInfo取得User
4. 将User存入UserContextHolder
5. 执行被加强方法
6. 清空UserContextHolder



#### fabric-gateway-plus

主要分为对Service方法的加强，加强原理跟fabric-security一样；对Contract接口的加强。

service，由于原理一致，只介绍加强方法：

1. 判断注解ChaincodeTransaction
2. 根据注解中的事务策略，创建对应的事务上下文
3. 取得GatewayInfo，根据gatewayInfo获取gateway，并存入GatewayContextHolder
4. 根据不同策略执行不同的执行前加强方法
5. 执行被加强代码
6. 根据不同策略执行不同的执行后加强方法
7. 如果报错，根据不同策略执行不同的执行报错加强方法
8. 返回结果



contract会通过spring提供的BeanDefinitionRegistryPostProcessor，来对Contract接口修改BeanDefinition，将其具体实现
=======
#### 介绍
{**以下是 Gitee 平台说明，您可以替换此简介**
Gitee 是 OSCHINA 推出的基于 Git 的代码托管平台（同时支持 SVN）。专为开发者提供稳定、高效、安全的云端软件开发协作平台
无论是个人、团队、或是企业，都能够用 Gitee 实现代码托管、项目管理、协作开发。企业项目请看 [https://gitee.com/enterprises](https://gitee.com/enterprises)}

#### 软件架构
软件架构说明


#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
>>>>>>> 1843df1f4859051f01a6ef2fe871e8b14914eb2c
