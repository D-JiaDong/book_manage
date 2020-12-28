function alter_book(pageSize, currentPage, bookid, opr){
    try{
        pageSize=document.bookForm.pageSize.value
        //通常来讲，这里的代码会从头到尾而不会产生任何问题
        //但有时会抛出一个异常，要么是由throw语句直接抛出，要么通过调用一个方法间接抛出
    }catch(e) {
        //当且仅当try语句块抛出了异常，才会执行这里的代码
        //这里可以通过局部变量e来获得对Error对象或者抛出的其他值的引用
        //这里的代码块可以基于某种原因处理这个异常，也可以忽略这个异常，还可以通过throw语句重新抛出异常
    }
    var booknum
    var bookname
    var Uid
    var Unum
    var Upri
    var Aname
    var Aauthor
    var Anum
    var Apri
    var Atypeid
    if(opr == 'submitUpdate'){
        Uid = document.bookForm.Uid.value
        Unum=document.bookForm.Unum.value
        Upri=document.bookForm.Upri.value
    }
    if(opr == 'submitAdd'){
        Aname=document.bookForm.Aname.value
        Aauthor=document.bookForm.Aauthor.value
        Anum=document.bookForm.Anum.value
        Apri=document.bookForm.Apri.value
        Atypeid=$("#booktype option:selected").val();
    }

    var bookname=document.bookForm.bookname.value
    var bookauthor=document.bookForm.bookauthor.value
    //  $ == jQuery  JSON对象
    $.ajax({
            url:'/Select_AllBookForAlterServlet',
            type: 'POST',
            data: JSON.stringify({
                currentPage:currentPage,
                book_id:bookid,
                pageSize: pageSize,
                opr: opr,
                bookauthor: bookauthor,
                bookname: bookname,
                Uid:Uid,
                Unum:Unum,
                Upri:Upri,
                Aname:Aname,
                Aauthor:Aauthor,
                Anum:Anum,
                Apri:Apri,
                Atypeid:Atypeid,
            }),
            dataType: 'json',
            success: function (data) {
                count = data.count
                totalPage = data.totalPage

                if (opr=='add'){
                    $('#modal-add').modal()
                    addbooktype=$('#addbooktype')
                    addbooktype.empty()
                    addbooktypeText= '<label for="booktype"  class="control-label" style="display: inline;">图书类型:</label><select name="booktype" id="booktype">'
                    for (i = 0; i < data.booktypes.length; i++) {
                        addbooktypeText += '<option value='+data.booktypes[i].type_id+' >'+data.booktypes[i].type_name+'</option>'
                    }
                    addbooktypeText += '</select>'
                    addbooktype.append(addbooktypeText)
                }else if(opr == 'update'){
                    $('#modal-update').modal()
                    document.bookForm.Uid.value =data.bookbyid[0]["book_id"]
                    document.bookForm.Unum.value =data.bookbyid[0]["book_num"]
                    document.bookForm.Upri.value =data.bookbyid[0]["book_price"]
                }else {
                    bodyObj = $('#bodyData')
                    text = ""
                    bodyObj.empty()
                    for ( i = (currentPage-1)*pageSize,j=0; i < data.data.length&&j<pageSize;i++,j++) {
                        text += "<tr><td>" + data.data[i]["book_id"] + "</td>" +
                            "<td>" + data.data[i]["book_name"] + "</td>" +
                            "<td>" + +"</td>" +
                            "<td>" + +"</td>" +
                            "<td>" + data.data[i]["book_author"] + "</td>" +
                            "<td>" + data.data[i]["book_price"] + "</td>" +
                            "<td>" + data.data[i]["book_num"] + "</td>"+
                            "<td>" +
                            " <a href=\"javascript:alter_book(" + pageSize + ',' + currentPage + ' , ' + data.data[i].book_id + ",\'update\')\">修改</a>" +
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
                        '                    <a href="javascript:alter_book(' + pageSize + ',' + 1 + ',' + 0 + ',\'select\'' + ')" >首页</a>' +
                        '                    <a href="javascript:alter_book(' + pageSize + ',' + (currentPage <= 1 ? 1 : currentPage - 1) + ',' + 0 + ',\'select\'' + ')" >上一页</a>' +
                        '                    <a href="javascript:alter_book(' + pageSize + ',' + (currentPage >= totalPage ? totalPage : currentPage + 1) + ',' + 0 + ',\'select\'' + ')"  >下一页</a>' +
                        '                    <a href="javascript:alter_book(' + pageSize + ',' + totalPage + ',' + 0 + ',\'select\''+ ')" >尾页</a>' +
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
                    for (i = 0; i < data.booktypes.length; i++) {
                        leftselectText += '<li id="sanwen"><a href="../admin/select_book.jsp?opr=' + data.booktypes[i].type_id + '">' + data.booktypes[i].type_name + '</a></li>'
                    }
                    select_ul.append(leftselectText) // DOM  innerHTML

                    //借阅管理菜单
                    borrow_ul = $('#borrow_ul')
                    borrow_ul.empty()
                    leftborrowText = '<li id="all"><a href="../admin/borrow_book_manage.jsp?opr=all">全部</a></li>'
                    for (i = 0; i < data.booktypes.length; i++) {
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
        alter_book(8, 1, 0, 'select')
    }
)
