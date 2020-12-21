function hotbook(pageSize, currentPage, opr) {
    try {
        pageSize=document.classForm.pageSize.value
        //通常来讲，这里的代码会从头到尾而不会产生任何问题
        //但有时会抛出一个异常，要么是由throw语句直接抛出，要么通过调用一个方法间接抛出
    } catch (e) {
        //当且仅当try语句块抛出了异常，才会执行这里的代码
        //这里可以通过局部变量e来获得对Error对象或者抛出的其他值的引用
        //这里的代码块可以基于某种原因处理这个异常，也可以忽略这个异常，还可以通过throw语句重新抛出异常
    }

    //  $ == jQuery  JSON对象
    $.ajax({
            url: '/booklistServlet',
            type: 'POST',
            data: JSON.stringify({
                opr:opr,
                pageSize:pageSize,
                currentPage:currentPage,
            }),
            dataType: 'json',
            success: function (data) {
                count=data.count
                totalPage=data.totalPage
                // data = JSON.parse(data)
                bodyObj = $('#bodyData')

                text = ""
                bodyObj.empty()
                for (i = (currentPage-1)*pageSize,j=0; i < data.data.length,j<pageSize;i++,j++) {
                    text += "<tr><td>" + data.data[i].book_id + "</td>" +
                        "<td>" + data.data[i].book_name + "</td>" +
                        "<td>" + + "</td>" +
                        "<td>" + + "</td>" +
                        "<td>" + data.data[i].book_author + "</td>" +
                        "<td>" + data.data[i].book_price + "</td>" +
                        "<td>" + data.data[i].book_num + "</td>"

                }
                bodyObj.append(text)



                //实现分页显示
                footObj = $('#pageData')
                footObj.empty()
                pageText = ' <tr align="right">' +
                    '                <td colspan="8">' +
                    '                   总共有' + count + '条 总共有' + totalPage + '页' +
                    '                   当前第' + currentPage + '页' +
                    '                    <a href="javascript:hotbook(' + pageSize + ',' + 1 + ',\'PopularSearch\')" >首页</a>' +
                    '                    <a href="javascript:hotbook(' + pageSize + ',' + (currentPage <= 1 ? 1 : currentPage - 1)+ ',\'PopularSearch\')" >上一页</a>' +
                    '                    <a href="javascript:hotbook(' + pageSize + ',' + (currentPage >= totalPage ? totalPage : currentPage + 1)  + ',\'PopularSearch\')" >下一页</a>' +
                    '                    <a href="javascript:hotbook(' + pageSize + ',' + totalPage +',\'PopularSearch\')">尾页</a>' +
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
        hotbook(8,1,"PopularSearch")
    }
)