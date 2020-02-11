function infoSubmit() {
    //获取信息
    var name = document.getElementById("logname").value;
    var password = document.getElementById("logpass").value;

    //信息格式
    var nameFormat = /^[a-zA-Z]{6,11}$/;
    var passwordFormat = /^[a-zA-Z]{6,11}$/;

    //判断信息格式
    if(name==null||name==""){
        document.getElementById("lognameVerify").innerHTML = "账号不能为空";
        return false;
    }
    else if(password==null||password==""){
        document.getElementById("lognameVerify").innerHTML = "密码不能为空";
        return false;
    }
    else if(!nameFormat.test(name)){
        document.getElementById("lognameVerify").innerHTML = "账号只能为英文，且在6~13之间";
        return false;
    }
    else if(!passwordFormat.test(password)){
        document.getElementById("lognameVerify").innerHTML = "密码只能为英文，且在6~13之间";
        return false;
    }
    else{
        document.getElementById("lognameVerify").innerHTML = "账号格式正确";
        return true;
    }
}
