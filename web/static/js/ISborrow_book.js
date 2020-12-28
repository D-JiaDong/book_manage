function ISborrowbook(pageSize, currentPage, type) {
    try {
        var search = window.location.search;
        type=search.split('=')[1]
        if(window.location.href.indexOf("select_book.jsp")>=0)
        {
            select_Li=document.getElementById("select_Li")
            select_Li.className="mm-active"

            select_a=document.getElementById("select_a")
            select_a.className="has-arrow waves-effect aria-expanded=true"

            select_ul=document.getElementById("select_ul")
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

    var bookname=document.bookForm.bookname.value
    var bookauthor=document.bookForm.bookauthor.value


    //  $ == jQuery  JSON对象
    $.ajax({
        url: '/Select_ISborrowBookServlet',
        type: 'POST',
        data: JSON.stringify({
            pageSize: pageSize,
            bookauthor: bookauthor,
            bookname: bookname,

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
                    text += "<tr><td>" + data.data[i]["book_id"] + "</td>" +
                        "<td>" + data.data[i]["book_name"] + "</td>" +
                        "<td>" + data.data[i]["book_author"] + "</td>"
                }
                bodyObj.append(text)



                //实现分页显示
                footObj = $('#pageData')
                footObj.empty()
                pageText = ' <tr align="right">' +
                    '                <td colspan="8">' +
                    '                   总共有' + count + '条 总共有' + totalPage + '页' +
                    '                   当前第' + currentPage + '页' +
                    '                    <a href="javascript:ISborrowbook(' + pageSize + ',' + 1 + ')" >首页</a>' +
                    '                    <a href="javascript:ISborrowbook(' + pageSize + ',' + (currentPage <= 1 ? 1 : currentPage - 1)+ ')" >上一页</a>' +
                    '                    <a href="javascript:ISborrowbook(' + pageSize + ',' + (currentPage >= totalPage ? totalPage : currentPage + 1)  + ')" >下一页</a>' +
                    '                    <a href="javascript:ISborrowbook(' + pageSize + ',' + totalPage +')">尾页</a>' +
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
                leftselectText='<li id="all"><a href="../user/select_book.jsp?opr=all">全部</a></li>'
                for(i=0;i<data.booktypes.length;i++){
                    leftselectText+='<li id="sanwen"><a href="../user/select_book.jsp?opr='+data.booktypes[i].type_id+'">'+data.booktypes[i].type_name+'</a></li>'
                }
                select_ul.append(leftselectText) // DOM  innerHTML

                //借阅管理菜单
                borrow_ul = $('#borrow_ul')
                borrow_ul.empty()
                leftborrowText='<li id="all"><a href="../user/borrow_book_manage.jsp?opr=all">全部</a></li>'
                for(i=0;i<data.booktypes.length;i++){
                    leftborrowText+='<li id="sanwen"><a href="../user/borrow_book.jsp?opr='+data.booktypes[i].type_id+'">'+data.booktypes[i].type_name+'</a></li>'
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
        
        ISborrowbook(8,1)
    }
)