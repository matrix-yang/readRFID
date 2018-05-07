# readRFID

## 安装识别RFID的模块方法为：

cordova plugin add https://github.com/matrix-yang/readRFID.git

## 卸载rfid模块的
cordova plugin rm readRFID

## 查看已安装插件
cordova plugin ls

## 识别rfid的插件的调用方法为：
```
cordova.plugins.readRFID.readRFID("use",function (msg) {  
 alert("success—>"+msg);                 #msg为返回的RFID的编号  
},function (err) {  
 alert("err--->"+err);  
});  
```
