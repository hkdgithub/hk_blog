function InfoSubmit() {

    //获取用户名
    var name = document.getElementById("name").value;
    //获取评论内容
    var textarea1 = document.getElementById("textarea1").value;

    //判断格式
    if(!name){
        alert("请输入用户名");
        return false;
    }
    else if(!textarea1){
        alert("请输入评论内容");
        return false;
    }
    else{
        alert("提交评论成功");
        return true;
    }

}