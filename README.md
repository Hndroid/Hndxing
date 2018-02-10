### Hndxing  

[![](https://jitpack.io/v/Hndroid/Hndxing.svg)](https://jitpack.io/#Hndroid/Hndxing)

#### 前言

Hndxing 是一个基于 [ZXing](https://github.com/zxing/zxing) 源码的基础上面，抽离其出 Android 端的扫码功能，而进一步封装而成的一个开源库 。

#### 特性

- 提供在竖屏状态下设别一维码、二维码的功能;
- 优化在竖屏状态识别一维码、二维码的速度、距离;

#### 效果

> 原生的 Hndroid 效果(因为虚拟机不具有摄像头，所以显示的是黑灰格)

![Hndxing 的原生效果](http://ouit3bg5b.bkt.clouddn.com/Peek%202018-02-10%2013-59.gif) 

> 由开发者自定义后的 Hndroid 效果

![由开发者自定义后的 Hndroid 效果](http://ouit3bg5b.bkt.clouddn.com/Peek%202018-02-10%2013-49.gif)

#### 使用

##### 添加对应权限

```
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.VIBRATE" />
    <uses-permission android:name="android.permission.FLASHLIGHT" />
```

##### 添加 Gradle：

> 把 JitPack 库加入到你的项目根目录下的 build.gradle 文件下

```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

> 添加依赖到你的项目中

```gradle
	dependencies {
	        compile 'com.github.Hndroid:Hndxing:1.0.1'
	}

```

##### or 添加 Maven：

```gradle
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
> 添加依赖到你的项目中

```gradle
	<dependency>
	    <groupId>com.github.Hndroid</groupId>
	    <artifactId>Hndxing</artifactId>
	    <version>1.0.1</version>
	</dependency>

```

##### 接下来添加两个布局控件到你的项目布局中：

```xml
    <SurfaceView 
            android:id="@+id/preview_view"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_gravity="center" />

    <com.makerspace.hndroid.hxing.view.ViewfinderView
            android:id="@+id/viewfinder_view"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
```

##### 继承抽象类 `BarCodeActivity` 并重写其三个方法：

```java
    @Override
    protected int provideContentView() {
        //返回上面添加的两个控件所在的布局
        return R.layout.activity_caputrue_layout;
    }

    @Override
    protected SurfaceView setSurfaceViewInstance() {
        //返回 SurfaceView 的对象引用
        return findViewById(R.id.preview_view);
    }

    @Override
    protected ViewfinderView setViewfinderViewInstance() {
        //返回 ViewfinderView 的对象引用
        return findViewById(R.id.viewfinder_view);
    }
```

##### 最后，在已经继承了抽象类 `BarCodeActivity` 的生命周期函数 `onCreate()` 回调扫码结果方法：

```java
@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setOnBarCodeResultListener(new OnResultListener() {
            @Override
            public void onResult(String resultContext, String barcodeFormatName) {
                //resultContext 表示已扫码的内容
                //barcodeFormatName 表示已扫码的种类
            }
        });
    }
``` 

##### 以上是调用 Hndroid 库的一般步骤;

#### 说明

> 为了使得 Android 开发者可以更加方便地调用 Hndxing 库，开发者可以根据实际项目开发的需要，继承 `ViewfinderView` 类，重写其 `onDraw(Canvas canvas)` 方法。下面是开放的其他接口：

|API|说明|
|---|---|
|CameraManager.get().turnLightOn();|打开手机的闪光灯;|
|CameraManager.get().turnLightOff();|关闭手机的闪光灯;|
|getBarcodeImage(String title);|调用系统的相册（title 是打开对话框时的标题）|

一般来说，调用 `getBarcodeImage(String title)` 是为了获取相册里面的二维码或其他的码类，调用该接口，需要在已经继承了抽象类 `BarCodeActivity` 的生命周期函数 `onCreate()` 回调扫码结果方法：

```java
@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setOnBarcodeImageChoseListener(new OnBarcodeImageChoseListener() {
            @Override
            public void onFail() {
                //获取二维码图片失败后回调的的方法;
            }

            @Override
            public void onSuccess(String resultContext, String barcodeFormatName) {
                //获取二维码图片成功后回调的的方法;
            }
        });
    }
```

----

> 注释：

- 如果你感兴趣，欢迎 Fork 这个项目，让我们一起更好地维护这个开源项目;
- 如果你在开发的过程中遇到问题，欢迎 Issues;
























