function alluser(pageSize, currentPage) {
    try {
        pageSize = document.classForm.pageSize.value
    }catch (e) {

    }

    var username=document.bookForm.username.value
    var usertel=document.bookForm.usertel.value


    //  $ == jQuery  JSON对象
    $.ajax({
        url: '/Select_AllUserServlet',
        type: 'POST',
        data: JSON.stringify({
            pageSize: pageSize,
            username: username,
            usertel: usertel,

        }),
            dataType: 'json',
            success: function (data) {
                count=data.count
                totalPage=data.totalPage

                // data = JSON.parse(data)

                bodyObj = $('#bodyData')
                text = ""
                bodyObj.empty()
                for ( i = (currentPage-1)*pageSize,j=0; i < data.data.length&&j<pageSize;i++,j++) {
                    text +="<tr><td><img src="+"\'"+data.data[i].user_pic+"\'"+" style="+"\'"+"width: 50px;height: 50px;left:0;top: 0;"+"\'"+">"+
                        "<td>" + data.data[i]["user_id"] + "</td>" +
                        "<td>" + data.data[i]["user_name"] + "</td>" +
                        "<td>" + data.data[i]["user_age"] + "</td>" +
                        "<td>" + data.data[i]["user_tel"] + "</td>" +
                        "<td>" + data.data[i]["user_intro"] + "</td>"+
                        "</tr>"
                }
                bodyObj.append(text)



                //实现分页显示
                footObj = $('#pageData')
                footObj.empty()
                pageText = ' <tr align="right">' +
                    '                <td colspan="8">' +
                    '                   总共有' + count + '条 总共有' + totalPage + '页' +
                    '                   当前第' + currentPage + '页' +
                    '                    <a href="javascript:alluser(' + pageSize + ',' + 1 + ')" >首页</a>' +
                    '                    <a href="javascript:alluser(' + pageSize + ',' + (currentPage <= 1 ? 1 : currentPage - 1)+ ')" >上一页</a>' +
                    '                    <a href="javascript:alluser(' + pageSize + ',' + (currentPage >= totalPage ? totalPage : currentPage + 1)  + ')" >下一页</a>' +
                    '                    <a href="javascript:alluser(' + pageSize + ',' + totalPage +')">尾页</a>' +
                    '                    <select name="pageSize" id="pageSize">' +
                    '                    <option value=\'3\'>3</option>' +
                    '                    <option value=\'5\' >5</option>' +
                    '                        <option value=\'8\' >8</option>' +
                    '                        <option value=\'10\'>10</option>' +
                    '                    </select>' +
                    '                </td>' +
                    '            </tr>'
                footObj.append(pageText) // DOM  innerHTML

                //查询的分类菜单
                select_ul = $('#select_ul')
                select_ul.empty()
                leftselectText='<li id="all"><a href="../admin/select_book.jsp?opr=all">全部</a></li>'
                for(i=0;i<data.booktypes.length;i++){
                    leftselectText+='<li id="sanwen"><a href="../admin/select_book.jsp?opr='+data.booktypes[i].type_id+'">'+data.booktypes[i].type_name+'</a></li>'
                }
                select_ul.append(leftselectText) // DOM  innerHTML

                //借阅管理菜单
                borrow_ul = $('#borrow_ul')
                borrow_ul.empty()
                leftborrowText='<li id="all"><a href="../admin/borrow_book_manage.jsp?opr=all">全部</a></li>'
                for(i=0;i<data.booktypes.length;i++){
                    leftborrowText+='<li id="sanwen"><a href="../admin/borrow_book_manage.jsp?opr='+data.booktypes[i].type_id+'">'+data.booktypes[i].type_name+'</a></li>'
                }
                borrow_ul.append(leftborrowText) // DOM  innerHTML


                var obj = document.getElementById('pageSize');
                for (i = 0; i < obj.length; i++) {
                    if (obj[i].value == pageSize)
                        obj[i].selected = true;
                }
            }
        }
    )
}
$(document).ready(
    function(){
        alluser(8,1)
    }
)