function checkUserName(){

    user_name = document.regForm.user_name.value
    if (user_name==""){
        alert("用户名不能为空")

    }
    else{
        // $.==jQuery. JSON对象
        //jsonObj = {a:1, b:2}
        //jsonObj.a = 100   不需要jsonObj[a]
        $.ajax({
                url: '/checkUserNameServlet',
                type: 'POST',
                data: JSON.stringify({   //把JSON对象转成JSON文本
                    user_name: user_name
                }),
                dataType:'json',
                success:function(data){
                    //data = JSON.parse(data)
                    if(data.isexist == '1'){
                        alert("用户名已经存在！")
                    }
                }
            }
        )
    }
}

function checkUserPwd() {
    user_pwd=document.regForm.user_pwd.value
    if(user_pwd==""){
        alert("密码不能为空")
    }
}
function checkUserRepwd() {
    user_pwd=document.regForm.user_pwd.value
    reUserPwd=document.regForm.reUserPwd.value
    if(user_pwd!=reUserPwd){
        alert("密码不一致")
    }
}