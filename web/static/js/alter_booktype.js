function alter_booktype(pageSize, currentPage, typeid, opr){
    try{
        pageSize=document.booktypeForm.pageSize.value
        //通常来讲，这里的代码会从头到尾而不会产生任何问题
        //但有时会抛出一个异常，要么是由throw语句直接抛出，要么通过调用一个方法间接抛出
    }catch(e) {
        //当且仅当try语句块抛出了异常，才会执行这里的代码
        //这里可以通过局部变量e来获得对Error对象或者抛出的其他值的引用
        //这里的代码块可以基于某种原因处理这个异常，也可以忽略这个异常，还可以通过throw语句重新抛出异常
    }
    var typename
    var Uid
    var Uname
    var Aname
    if(opr == 'submitUpdate'){
        Uid = document.booktypeForm.Uid.value
        Uname=document.booktypeForm.Uname.value
    }
    if(opr == 'submitAdd'){
        Aname=document.booktypeForm.Aname.value
    }

    var typename=document.booktypeForm.typename.value
    //  $ == jQuery  JSON对象
    $.ajax({
            url:'/Select_AlltypeForAlterServlet',
            type: 'POST',
            data: JSON.stringify({
                currentPage:currentPage,
                type_id:typeid,
                pageSize: pageSize,
                opr: opr,
                typename: typename,
                Uid:Uid,
                Uname:Uname,
                Aname:Aname,
            }),
            dataType: 'json',
            success: function (data) {
                count = data.count
                totalPage = data.totalPage

                if (opr=='add'){
                    $('#modal-add').modal()
                }else if(opr == 'update'){
                    $('#modal-update').modal()
                    document.booktypeForm.Uname.value =data.booktypebyid[0]["type_name"]
                    document.booktypeForm.Uid.value =data.booktypebyid[0]["type_id"]
                }else {
                    bodyObj = $('#bodyData')
                    text = ""
                    bodyObj.empty()
                    for ( i = (currentPage-1)*pageSize,j=0; i < data.data.length&&j<pageSize;i++,j++) {
                        text += "<tr><td>" + data.data[i]["type_id"] + "</td>" +
                            "<td>" + data.data[i]["type_name"] + "</td>" +
                            "<td>" +
                            " <a href=\"javascript:alter_booktype(" + pageSize + ',' + currentPage + ' , ' + data.data[i].type_id + ",\'update\')\">修改</a>" +
                            "</td>" +
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
                        '                    <a href="javascript:alter_booktype(' + pageSize + ',' + 1 + ',' + 0 + ',\'select\'' + ')" >首页</a>' +
                        '                    <a href="javascript:alter_booktype(' + pageSize + ',' + (currentPage <= 1 ? 1 : currentPage - 1) + ',' + 0 + ',\'select\'' + ')" >上一页</a>' +
                        '                    <a href="javascript:alter_booktype(' + pageSize + ',' + (currentPage >= totalPage ? totalPage : currentPage + 1) + ',' + 0 + ',\'select\'' + ')"  >下一页</a>' +
                        '                    <a href="javascript:alter_booktype(' + pageSize + ',' + totalPage + ',' + 0 + ',\'select\''+ ')" >尾页</a>' +
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
                    leftselectText = '<li id="all"><a href="../admin/select_book.jsp?opr=all">全部</a></li>'
                    for (i = 0; i < data.data.length; i++) {
                        leftselectText += '<li id="sanwen"><a href="../admin/select_book.jsp?opr=' + data.booktypes[i].type_id + '">' + data.booktypes[i].type_name + '</a></li>'
                    }
                    select_ul.append(leftselectText) // DOM  innerHTML

                    //借阅管理菜单
                    borrow_ul = $('#borrow_ul')
                    borrow_ul.empty()
                    leftborrowText = '<li id="all"><a href="../admin/borrow_book_manage.jsp?opr=all">全部</a></li>'
                    for (i = 0; i < data.data.length; i++) {
                        leftborrowText += '<li id="sanwen"><a href="../admin/borrow_book_manage.jsp?opr=' + data.booktypes[i].type_id + '">' + data.booktypes[i].type_name + '</a></li>'
                    }
                    borrow_ul.append(leftborrowText) // DOM  innerHTML


                }
                var obj = document.getElementById('pageSize');

                for (i = 0; i < obj.length; i++) {
                    if (obj[i].value == pageSize)
                        obj[i].selected = true;
                }
                if(opr == 'submitUpdate' || opr == 'submitAdd'){
                    $('#modal-update').modal('hide')
                    $('#modal-add').modal('hide')
                }
            }
        }
    )
}
$(document).ready(
    function(){
        alter_booktype(8, 1, 0, 'select')
    }
)
