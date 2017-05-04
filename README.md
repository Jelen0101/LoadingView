![](image1.png)
<h1>Usage</h1>

<h4>Step 1</h4>
*Project Gradle:*
```
    allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
            }
            }
```
*App Gradle:*
```
   dependencies {
   	        compile 'com.github.Jelen0101:LoadingView:v1.0.0'
   	}
```
<h4>Step 2</h4>
```java
  mLoadingView = new LoadingView.Builder(this)
                .text("加载中")
                .build();
  mLoadingView.showInCenter();
```

<h1>Thanks</h1>
[AVLoadingIndicatorView](https://github.com/81813780/AVLoadingIndicatorView)
