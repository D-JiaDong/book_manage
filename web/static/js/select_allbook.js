function allbook(pageSize, currentPage, opr) {
    try {

        var search = window.location.search;
        var Opr=search.split('=')
        opr=Opr[1]
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
        url: '/Select_AllBookServlet',
        type: 'POST',
        data: JSON.stringify({
            opr: opr,
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
                        "<td>" + +"</td>" +
                        "<td>" + +"</td>" +
                        "<td>" + data.data[i]["book_author"] + "</td>" +
                        "<td>" + data.data[i]["book_price"] + "</td>" +
                        "<td>" + data.data[i]["book_num"] + "</td>"

                }
                bodyObj.append(text)



                //实现分页显示
                footObj = $('#pageData')
                footObj.empty()
                pageText = ' <tr align="right">' +
                    '                <td colspan="8">' +
                    '                   总共有' + count + '条 总共有' + totalPage + '页' +
                    '                   当前第' + currentPage + '页' +
                    '                    <a href="javascript:allbook(' + pageSize + ',' + 1 + ',\'PopularSearch\')" >首页</a>' +
                    '                    <a href="javascript:allbook(' + pageSize + ',' + (currentPage <= 1 ? 1 : currentPage - 1)+ ',\'PopularSearch\')" >上一页</a>' +
                    '                    <a href="javascript:allbook(' + pageSize + ',' + (currentPage >= totalPage ? totalPage : currentPage + 1)  + ',\'PopularSearch\')" >下一页</a>' +
                    '                    <a href="javascript:allbook(' + pageSize + ',' + totalPage +',\'PopularSearch\')">尾页</a>' +
                    '                    <select name="pageSize" id="pageSize">' +
                    '                    <option value=\'3\'>3</option>' +
                    '                    <option value=\'5\' >5</option>' +
                    '                        <option value=\'8\' >8</option>' +
                    '                        <option value=\'10\'>10</option>' +
                    '                    </select>' +
                    '                </td>' +
                    '            </tr>'
                footObj.append(pageText) // DOM  innerHTML


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
        var opr=search.split('=')
        allbook(8,1,opr[1])
    }
)