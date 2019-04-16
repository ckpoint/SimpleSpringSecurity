
# Simple-Spring-Security

It is a library that makes Spring Security easy to use to spring boot 2.0

Simple Spring Security depends on [Model Mapper](https://github.com/modelmapper/modelmapper), [Project Lombok](http://projectlombok.org/)

# Installation

#### 1. MAVEN
```xml
<dependency>
  <groupId>com.github.ckpoint</groupId>
  <artifactId>simple-spring-security</artifactId>
  <version>0.0.2</version>
</dependency>

```
#### 2. GRADLE
```gradle
  compile group: 'com.github.ckpoint', name: 'simple-spring-security', version: '0.0.2'
```

# Usage

## Table of Contents
- [ 1. Add Simple UserDetails ](#add-simple-userdetails)
- [ 2. Add Simple Spring Security Service](#add-simple-spring-security-service)
- [ 3. Change Password Encoder (Optional)](#change-password-encoder)
- [ 4. Encode password ](#encode-password)
- [ 5. Add Login Controller ](#add-login-controller)
- [ 6. Properties ](#properties)

## Add Simple UserDetails

#### A. Define a class that extends simpleUserDetails.
#### B. Defines the field to be saved in the session
#### C. One of getRole and getRoles must be filled

```java
public class TestUserDetail extends SimpleUserDetails {

    private Long id;
    private String userName;
    private String password;
    private String role;

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    protected String getRole() {
        return this.role;
    }

    @Override
    protected List<String> getRoles() {
        return null;
    }
}
```

## Change Password Encoder (Optional)

#### Simply override the createPasswordEncoder function in the SimpleSecurityService and return the encoder you want to use.
#### The default password encoder is BCryptPasswordEncoder.

```java

@Service
@RequiredArgsConstructor
public class TestSecurityService extends SimpleSecurityService {

    ....

    @Override
    protected PasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

```


## Encode Password

#### The password encoder, currently used by security, is stored by the simplePasswordEncoder class.
#### You can encrypt the plaintext by calling SimplePasswordEncoder encodePassword function.

```java
    SimplePasswordEncoder.encodePassword("1234");
```

## Add Simple Spring Security Service


#### A. Define a class that extends SimpleSecurityService.
#### B. In the loadUserByUsername function, write code that finds the user in the existing user name.
#### C. Set the per-path authentication in the configure function.
#### D. The csrf and cors filter are set through the overrides of the isUseCsrf and isUseCors functions, respectively.

```java
@Service
@RequiredArgsConstructor
public class TestSecurityService extends SimpleSecurityService {

    @NonNull
    private final AccountService accountService;

    @Override
    public SimpleUserDetails loadUserByUsername(String userName) {
        return new TestUserDetail().updateFromObj(accountService.findAccountFromUserName(userName));
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/public/**").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    public boolean isUseCsrf() {
        return false;
    }

    @Override
    public boolean isUseCors() {
        return true;
    }

}

```

## Add Login Controller

#### Finally, add the following login controller.



```java
@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class AccountController {

    @NonNull
    private final TestSecurityService testSecurityService;

    @PostMapping("/login")
    public AccountModel login(@RequestBody AccountModel accountModel, HttpSession httpSession) {
        return this.testSecurityService.login(accountModel.getUserName(), accountModel.getPassword(), AccountModel.class, httpSession);
    }

    @GetMapping("/session")
    public TestUserDetail sessionCheck(TestUserDetail testUserDetail) {
        return testUserDetail;
    }

}
```

#### !! If the parameter of the controller extends SimpleUserDetails, it automatically passes the information stored in the session to the controller parameter.


## Properties

#### simple.security.csrf.enable : csrf enable setting - default value true
#### simple.security.cors.enable : cors enable setting - default value false
#### simple.security.cors.url : cors mapping pattern - default value /**
#### simple.security.cors.origins : cors origins setting - default value *
#### simple.security.cors.methods : cors methods setting - default value *
#### simple.security.cors.headers : cors headers setting - default value *
