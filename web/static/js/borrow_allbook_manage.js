function borrow_allbook_manage(pageSize, currentPage,type,opr,book_id,book_num,user_id) {
    try {
        var search = window.location.search;
        type=search.split('=')[1]
        if(window.location.href.indexOf("borrow_book_manage.jsp")>=0)
        {
            select_Li=document.getElementById("borrow_Li")
            select_Li.className="mm-active"

            select_a=document.getElementById("borrow_a")
            select_a.className="has-arrow waves-effect aria-expanded=true"

            select_ul=document.getElementById("borrow_ul")
            select_ul.className="sub-menu mm-collapse mm-show aria-expanded='false'"
        }

    }
    catch (e) {
        alert("出错")
    }
    try {
        pageSize = document.classForm.pageSize.value
    }catch (e) {

    }


    //查询编表单提交
    var bookname=document.bookForm.bookname.value
    var bookauthor=document.bookForm.bookauthor.value
    var username=document.bookForm.username.value
    var userid=document.bookForm.userid.value
    var bookid=document.bookForm.bookid.value
    //  $ == jQuery  JSON对象
    $.ajax({
        url: '/Select_AllUser_BookServlet',
        type: 'POST',
        data: JSON.stringify({
            opr: opr,
            type: type,
            //表单
            pageSize: pageSize,
            bookauthor: bookauthor,
            bookname: bookname,
            bookid:bookid,
            userid:userid,
            username:username,
            //归还
            book_id: book_id,
            book_num:book_num,
            user_id:user_id,

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
                    text += "<tr><td>" + data.data[i]["User_id"] + "</td>" +
                        "<td>" + data.data[i]["User_name"] + "</td>" +
                        "<td>" + data.data[i]["Book_id"] + "</td>" +
                        "<td>" + data.data[i]["Book_name"] + "</td>" +
                        "<td>" + "<input type='button' class='button-examples'  value='确认归还' onclick='borrow_allbook_manage("+pageSize+','+currentPage+','+type+','+'\"return\"'+','+data.data[i]["Book_id"]+','+data.data[i]["Book_num"]+','+data.data[i]["User_id"]+")'>"

                }
                bodyObj.append(text)



                //实现分页显示
                footObj = $('#pageData')
                footObj.empty()
                pageText = ' <tr align="right">' +
                    '                <td colspan="8">' +
                    '                   总共有' + count + '条 总共有' + totalPage + '页' +
                    '                   当前第' + currentPage + '页' +
                    '                    <a href="javascript:borrow_allbook_manage(' + pageSize + ',' + 1 + ','+type+',\'select\',' +0+','+0+','+0+')" >首页</a>' +
                    '                    <a href="javascript:borrow_allbook_manage(' + pageSize + ',' + (currentPage <= 1 ? 1 : currentPage - 1)+ ','+type+',\'select\',' +0+','+0+','+0+')" >上一页</a>' +
                    '                    <a href="javascript:borrow_allbook_manage(' + pageSize + ',' + (currentPage >= totalPage ? totalPage : currentPage + 1) + ','+type+ ',\'select\',' +0+','+0+','+0+')"  >下一页</a>' +
                    '                    <a href="javascript:borrow_allbook_manage(' + pageSize + ',' + totalPage + ','+type+',\'select\',' +0+','+0+','+0+')" >尾页</a>' +
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


                if(opr=="return"){
                    if(data.return_result==0) {
                        alert("失败")
                    }else {
                        alert("操作成功")
                    }

                }
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

        var search = window.location.search;
        var type=search.split('=')
        borrow_allbook_manage(8,1,type,'select',0,0,0)
    }
)