function updateBlogState() {
    //获取该标签的状态
    var blogState = document.getElementById("blogState").value;
    alert(blogState);
}

//判断是否删除博客
function deleteBlog() {

    if(confirm("确定删除该条博客")){
        return true;
    }
    else {
        return false;
    }
}

//判断是否删除评论
function deleteComment() {

    if(confirm("是否将评论和回复都删除")){
        return true;
    }
    else {
        return false;
    }
}

//判断用户输入的信息是否完整
function writeBlogSubmit() {

    //博客标题
    var blogTitle = document.getElementById("blogTitle").value;
    //博客分类
    var blogCategoryId = document.getElementById("blogCategoryId").value;
    //博客标签
    var blogTagsId = document.getElementById("blogTagsId").value;
    //博客内容
    var blogContent = document.getElementById("blogContent").value;
    //博客预览图
    var blogCoverImage = document.getElementById("blogCoverImage").value;
    //开始判断
    if(!blogTitle||!blogCategoryId||!blogTagsId||!blogContent||!blogCoverImage) {
        alert("请将信息填写完整");
        return false;
    }
    else {
        return true;
    }

}

//判断修改博客内容是否完整
function updateBlog() {

    //博客标题
    var blogTitle = document.getElementById("blogTitle1").value;
    //博客分类
    var blogCategoryId = document.getElementById("blogCategoryId1").value;
    //博客标签
    var blogTagsId = document.getElementById("blogTagsId1").value;
    //博客内容
    var blogContent = document.getElementById("blogContent1").value;

    //开始判断
    if(!blogTitle||!blogCategoryId||!blogTagsId||!blogContent){
        alert("请将信息填写完整");
        return false;
    }
    else{
        return true;
    }
}

//判断发送邮件是否完整
function sendEmailSubmit(){

    //邮件标题
    var subject = document.getElementById("subject").vaule;
    //邮件内容
    var content = document.getElementById("content").vaule;

    //开始判断
    if(!subject||!content){
        alert("请将信息填写完整");
        return false;
    }
    else{
        return true;
    }
}

//判断博客评论格式是否正确
function addBlogCommentInfo() {

    //获取昵称
    var name = document.getElementById("name").value;
    //获取性别
    var sex = document.getElementById("sex").value;
    //获取邮箱
    var email = document.getElementById("email").value;
    //获取内容
    var textarea1 = document.getElementById("textarea1").value;

    var reg = new RegExp(/^\S+@\S+\.\S{2,}$/);


//开始判断
    if(!name||!sex||!email||!textarea1){
        alert("请将信息填写完整");
        return false;
    }
    if(sex!=="男" && sex!=="女"){
        alert("你是不男不女吗");
        return  false;
    }
    if(!reg.test(email)){
        alert("邮箱格式错误");
        return false;
    }
    else {
        return true;
    }
}

//判断更改博客内容是否为空
function updateBlogSubmit() {

    //博客标题
    var blogTitle1 = document.getElementById("blogTitle1").value;
    //博客内容
    var blogContent1 = document.getElementsById("blogContent1").value;

    //开始判断
    if(!blogTitle1 || !blogContent1){
        alert("请将信息填写完整");
        return false;
    }
    else {
        return true;
    }

}

//打开后台主界面的拟态框
function openAdminMainModal(obj) {

    var $td = $(obj).parents('tr').children('td');
    var blogId = $td.eq(0).text();
    var blogTitle = $td.eq(1).text();
    var blogCategoryId = $td.eq(5).text();
    var blogTagsId = $td.eq(6).text();


    $("#blogId1").val(blogId);
    $("#blogTitle1").val(blogTitle);
    $("#blogCategoryId1").val(blogCategoryId);
    $("#blogTagsId1").val(blogTagsId);

}

