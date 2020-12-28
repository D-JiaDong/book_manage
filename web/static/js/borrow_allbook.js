function borrow_allbook(pageSize, currentPage,type,opr,book_id,book_num) {
    try {
        var search = window.location.search;
        type=search.split('=')[1]
        if(window.location.href.indexOf("borrow_book.jsp")>=0)
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

    var bookname=document.bookForm.bookname.value
    var bookauthor=document.bookForm.bookauthor.value

    //  $ == jQuery  JSON对象
    $.ajax({
        url: '/Select_AllBookServlet',
        type: 'POST',
        data: JSON.stringify({
            opr: opr,
            type: type,
            pageSize: pageSize,
            bookauthor: bookauthor,
            bookname: bookname,
            book_id: book_id,
            book_num:book_num,

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
                        "<td>" + data.data[i]["book_num"] + "</td>"+
                        "<td>" + "<input type='button' class='button-examples'  value='借阅' onclick='borrow_allbook("+pageSize+','+currentPage+','+type+','+'\"borrow\"'+','+data.data[i].book_id+','+data.data[i].book_num+")'>"

                }
                bodyObj.append(text)



                //实现分页显示
                footObj = $('#pageData')
                footObj.empty()
                pageText = ' <tr align="right">' +
                    '                <td colspan="8">' +
                    '                   总共有' + count + '条 总共有' + totalPage + '页' +
                    '                   当前第' + currentPage + '页' +
                    '                    <a href="javascript:borrow_allbook(' + pageSize + ',' + 1 + ','+type+',\'select\',' +0+','+0+')" >首页</a>' +
                    '                    <a href="javascript:borrow_allbook(' + pageSize + ',' + (currentPage <= 1 ? 1 : currentPage - 1)+ ','+type+',\'select\',' +0+','+0+')" >上一页</a>' +
                    '                    <a href="javascript:borrow_allbook(' + pageSize + ',' + (currentPage >= totalPage ? totalPage : currentPage + 1) + ','+type+ ',\'select\',' +0+','+0+')"  >下一页</a>' +
                    '                    <a href="javascript:borrow_allbook(' + pageSize + ',' + totalPage + ','+type+',\'select\',' +0+','+0+')" >尾页</a>' +
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
                leftborrowText='<li id="all"><a href="../user/borrow_book.jsp?opr=all">全部</a></li>'
                for(i=0;i<data.booktypes.length;i++){
                    leftborrowText+='<li id="sanwen"><a href="../user/borrow_book.jsp?opr='+data.booktypes[i].type_id+'">'+data.booktypes[i].type_name+'</a></li>'
                }
                borrow_ul.append(leftborrowText) // DOM  innerHTML


                if(opr=="borrow"){
                    if(data.borrow_result==0) {
                        alert("图书现存不够 无法借阅")
                    }else if(data.borrow_result==-1) {
                        alert("发生错误 借阅失败")
                    }else if(data.borrow_result==-2){
                            alert("借过该图书 尚未归还")
                    }else {
                        alert("借阅成功")
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
        borrow_allbook(8,1,type,'select',0,0)
    }
)