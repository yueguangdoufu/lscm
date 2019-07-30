$(function(){
    let tempArr=[],isSearch=false; //存储临时查询结果数组
    let curPages = 0;   //分页页码初始页
    //默认显示成都外国语学校
    $(studentArray).each(function(i,item){
        if(item.schName=="成都外国语学校"){
            stuArr.push(item)
        }
    });
    studentTableInforList(curPages);
    function studentTableInforList(pageNumber){
        let studentManagementTbodyList = $("#studentManagementTbody");
        let Array=iSearch();
        curPages = pageNumber;
        let indexNum = pageNumber * 8;
        for(let i=0;i<8;i++){
            if (indexNum + i < Array.length) {
                studentManagementTbodyList.append(`<tr>
            <td><input type="checkbox"/></td>
            <td class="hidden">${Array[indexNum +i].stuId}</td>
            <td>${Array[indexNum +i].stuName}</td>
            <td>${Array[indexNum +i].stuSex}</td>
            <td>${Array[indexNum +i].className}</td>
            <td>${Array[indexNum +i].stuVIPState}</td>
            <td>${Array[indexNum +i].stuTel}</td>
            <td>
                <span class="studentListRead" data-check="check" data-toggle="modal"  data-target="#exampleModal-Read" title="查看"></span>
                <span class="studentListEdit" data-toggle="modal" data-upDataInf="upDataInf" data-target="#exampleModal" title="编辑"></span>
                <span class="studentListAddContent" data-toggle="modal" data-upDataInf="upDataInf" data-target="#exampleModal-Record" title="添加回访记录"></span>
                <span class="studentListDel" data-toggle="modal"  data-target="#exampleModal-del" title="删除"> </span>
            </td>
        </tr>`)
            }
        }
        studentListReadClick(Array);
        studentEditInfClick(Array);
    }
    //页码生成
    switcherStyle();
    //创建页
    function switcherStyle(){
        $(".pagination li:not(:first,:last)").remove();
        let Array=iSearch();
        for(let i=0;i<(Math.ceil(Array.length / 8));i++){
            $(".switcher .pagination li:last-child").before(`<li><span data-index=${i}>${i+1}</span></li>`)
        }
        //切换
        $(".pagination>li:not(:first,:last)>span").click(function(){
            curPages=$(this).attr("data-index");
            pageUpdate(curPages);//页面更新
        });
    }
//上一页
    $(".pagination>li:first").click(function(){
        if(curPages>0)curPages--;
        pageUpdate(curPages);//页面更新
    });
//下一页
    $(".pagination>li:last").click(function(){
        let Array=iSearch();
        if(curPages<(Math.ceil(Array.length / 8))-1)curPages++;
        pageUpdate(curPages);//页面更新
    });
//给查看按钮添加点击事件，点击后从数组中获取值给模态框
    function studentListReadClick(Array){
        $(".studentListRead").on("click", function () {
            //查找点击行的对应的studentId
            let index = parseInt($(this).parents("tr").find(".hidden").eq(0).text());
            let recordStuId = index;
            //循环找到与id相同的数组下标
            for (let i = 0; i < Array.length; i++) {
                if (Array[i].stuId === index) {
                    index = i;
                    break;
                }
            }
//            将下标赋值给模态框中的对应处
            $("#message-stuId-Read").val(index);
            //更改弹出模态框的标题和按钮字。
            $(".modal-title").text("查看学生信息");
            //获取原本的数据
            $("#exampleModal-Read").find("input,textarea").prop("readOnly", true);
            $(".tableRecord").prop("readOnly", true);
            $("#message-stuName-Read").val(Array[index].stuName);
            $("#message-stuBirth-Read").val(Array[index].stuBirth);
            $("#message-stuAddress-Read").val(Array[index].stuAddress);
            $("#message-stuFather-Read").val(Array[index].stuFather);
            $("#message-stuMother-Read").val(Array[index].stuMother);
            $("#message-stuSex-Read").val(Array[index].stuSex);
            $("#message-className-Read").val(Array[index].className);
            $("#message-stuTel-Read").val(Array[index].stuTel);
            $("#message-stuFatherTel-Read").val(Array[index].stuFatherTel);
            $("#message-stuMotherTel-Read").val(Array[index].stuMotherTel);
            $("#message-stuVIPState-Read").val(Array[index].stuVIPState);
            $("#message-stuRemarks-Read").val(Array[index].stuRemarks);
            for(let j=0;j<studentReturnVisitRecordArray.length;j++){
                if(recordStuId==studentReturnVisitRecordArray[j].stuId){
                    $("#studentRecordTbody").append(`<tr>
                    <td class="hidden">${studentReturnVisitRecordArray[j].stuRVRId}</td>
                    <td class="hidden">${studentReturnVisitRecordArray[j].stuId}</td>
                    <td>${studentReturnVisitRecordArray[j].stuRVRTime}</td>
                    <td>${studentReturnVisitRecordArray[j].stuRVRCharge}</td>
                    <td>${studentReturnVisitRecordArray[j].stuRVRparson}</td>
                    <td>${studentReturnVisitRecordArray[j].stuRVRTitle}</td>
                    <td>${studentReturnVisitRecordArray[j].stuRVRContent}</td>
        </tr>`)
                }
            }
            $(".thatIsOk").on("click",function(){
                $("#studentRecordTbody").html("");//操作完毕后所有字段置空
            })
        });
    }

//给编辑按钮添加点击事件，点击后从数组中获取值给模态框
    function studentEditInfClick(Array){
        $(".studentListEdit").on("click", function () {
            //查找点击行的对应的studentId
            let index = parseInt($(this).parents("tr").find(".hidden").eq(0).text());
            //循环找到与id相同的数组下标
            $("#message-stuId").val(index-1);
            for (let i = 0; i < Array.length; i++) {
                if (Array[i].stuId === index) {
                    index = i;
                    break;
                }
            }
//            将下标赋值给模态框中的对应处
            $("#message-stuId-search").val(index);
            //更改弹出模态框的标题和按钮字。
            //$(".modal-title").text("修改学生信息");
            $("#submitInf").text("确认");
            //获取原本的数据
            $("#exampleModal").find("input,textarea").prop("readOnly", false);
            $("#message-stuName").val(Array[index].stuName);
            $("#message-stuBirth").val(Array[index].stuBirth);
            $("#message-stuAddress").val(Array[index].stuAddress);
            $("#message-stuFather").val(Array[index].stuFather);
            $("#message-stuMother").val(Array[index].stuMother);
            $("#message-stuSex").val(Array[index].stuSex);
            $("#message-className").val(Array[index].className);
//            $("#message-className").val();
            $("#message-stuTel").val(Array[index].stuTel);
            $("#message-stuFatherTel").val(Array[index].stuFatherTel);
            $("#message-stuMotherTel").val(Array[index].stuMotherTel);
            $("#message-stuVIPState").val(Array[index].stuVIPState);
            $("#message-stuRemarks").val(Array[index].stuRemarks);
        });
    }
//给添加回访记录按钮添加点击事件，点击后根据当前学生ID获取输入值给模态框
    $(".studentListAddContent").on("click", function () {
        //查找点击行的对应的studentId
        let index = parseInt($(this).parents("tr").find(".hidden").eq(0).text());
        //循环找到与id相同的数组下标
        $("#message-stuId-Record").val(index-1);
        //更改弹出模态框的标题和按钮字。
        $(".modal-title").text("添加回访记录");
        $("#submitRecord").text("确认");
    });
//获取添加回访记录模态框输入的数据
    $("#submitRecord").on("click", function () {
        //修改数据
        const index = parseInt($("#message-stuId-Record").val());
        let schoolTemp = {
            stuRVRId:0,stuId: 0, stuRVRTime: "", stuRVRCharge: "", stuRVRparson: "", stuRVRTitle: "", stuRVRContent: ""
        };
        schoolTemp.stuRVRId = studentReturnVisitRecordArray.length+1;
        schoolTemp.stuId = index+1;
        schoolTemp.schName = stuArr[0].schName;
        let d = new Date();
        schoolTemp.stuRVRTime = (d.getFullYear()+"-"+ (d.getMonth()+1)+"-"+ d.getDate());
        schoolTemp.stuRVRCharge = $("#message-stuRVRCharge-Record").val();
        schoolTemp.stuRVRparson = $("#message-stuRVRparson-Record").val();
        schoolTemp.stuRVRTitle = $("#message-stuRVRTitle-Record").val();
        schoolTemp.stuRVRContent = $("#message-stuRVRContent-Record").val();
        //数组数据添加
        studentReturnVisitRecordArray.push(schoolTemp);
        $(".hideModeTab").find("input").val("");//操作完毕后所有字段置空
        pageUpdate(curPages);//表格更新
    });
//模态窗隐藏清空值
    $("#exampleModal").on("hide.bs.modal", function () {
        $("#exampleModal").find("textarea,input").val("");
        $("#submitInf").show();
    });
//点击模态框提交按钮提交数据,接着清空表单
    $("#submitInf").on("click", function () {
        //修改数据
        const index = parseInt($("#message-stuId").val());
        const seaIndex = parseInt($("#message-stuId-search").val());
        let schoolTemp = {
            stuId:0,schId: 0, schName: "", stuName: "", stuSex: "", stuBirth: "", stuAddress: "",
            ClassId:0, className: "", stuTel: "", stuFather: "", stuFatherTel: "", stuVIPState: "",
            stuMother: "", stuMotherTel: "", stuRemarks: ""
        };
        schoolTemp.stuId = index+1;
        schoolTemp.schId = 1;
        schoolTemp.schName = stuArr[0].schName;
        schoolTemp.stuName = $("#message-stuName").val();
        schoolTemp.stuSex = $("#message-stuSex").val();
        schoolTemp.stuBirth = $("#message-stuBirth").val();
        schoolTemp.stuAddress = $("#message-stuAddress").val();
//  schoolTemp.ClassId = stuArr[index].ClassId;
        schoolTemp.className = $("#message-className").val();
        let schNameInfor = schoolTemp.className;
        if(schNameInfor=="网脉一班"){
            schoolTemp.ClassId = 1;
        }else if(schNameInfor=="网脉二班"){
            schoolTemp.ClassId = 2;
        }else if(schNameInfor=="网脉三班"){
            schoolTemp.ClassId = 3;
        }
        schoolTemp.stuTel = $("#message-stuTel").val();
        schoolTemp.stuFather = $("#message-stuFather").val();
        schoolTemp.stuFatherTel = $("#message-stuFatherTel").val();
        schoolTemp.stuVIPState = $("#message-stuVIPState").val();
        schoolTemp.stuMother = $("#message-stuMother").val();
        schoolTemp.stuMotherTel = $("#message-stuMotherTel").val();
        schoolTemp.stuRemarks = $("#message-stuRemarks").val();
        //修改
        if (index||index === 0) {
            stuArr[index] = schoolTemp;
            tempArr[seaIndex]=schoolTemp;
        }
        //全选删除后新增
        else if(stuArr.length==0){
            schoolTemp.schId = 1;
            stuArr.push(schoolTemp);
            tempArr.push(schoolTemp);
            //新增
        } else {
            schoolTemp.stuId = stuArr[stuArr.length - 1].stuId + 1;
            stuArr.push(schoolTemp);
            tempArr.push(schoolTemp);
        }
        $(".hideModeTab").find("input").val("");//操作完毕后所有字段置空
        pageUpdate(curPages);//表格更新
    });
//删除一行
    $("#studentManagement").on("click",".studentListDel",function(){
        let that = this;
        //找到所选删除行的id
        let listIndexA = $(this).parents("tr").find(".hidden").eq(0).text();
        $("#submitInf-del").on("click",function(){
            let listIndex = listIndexA;
            //    在数组中循环找到对应ID的下标
            for(let i = 0;i<stuArr.length;i++){
                if(listIndex==stuArr[i].stuId){
                    stuArr.splice(i, 1);//数组中删除
                    $(that).parent().parent().remove();//页面中删除
                    break;
                }
            }
            if(isSearch){
                for(let i = 0;i<tempArr.length;i++){
                    if(listIndex==tempArr[i].stuId){
                        tempArr.splice(i, 1);//数组中删除
                        $(that).parent().parent().remove();//页面中删除
                        break;
                    }
                }
            }
            pageUpdate(curPages);//页面更新
        })
    });
//判断是否是搜索出来的数据
    function iSearch(){
        let Array=[];
        if(isSearch){
            Array=tempArr;
        }else{
            Array=stuArr;
        }
        return Array;
    }
//执行操作后更新页面数据
    function pageUpdate(curPages){
        //页面没有数据时更新当前页
        if( $("#studentManagementTbody").html()==""){
            curPages=0;
        }
        $("#studentManagementTbody").html("");//清空列表信息
        studentTableInforList(curPages);//重新加载列表信息
        switcherStyle();
    }
//新增数据
    $("#addNew1").on("click", function () {
//        $('#exampleModal').on('show.bs.modal', function () {
        $(".modal-title").text("新增用户信息");
        $("#submitInf").text("确认");
//        });
    });
//全选按钮的全选与全部取消选中
    $("#selectAll").on("click", function () {
        if ($(this).prop("checked") === true) {
            $("#studentManagementTbody").find("input").each(function (index, element) {
                $(element).prop("checked", true);
            });
        } else {
            $("#studentManagementTbody").find("input").each(function (index, element) {
                $(element).prop("checked", false);
            });
        }
    });
//复选框单选取消全选按钮的选中
    $("#studentManagementTbody").find("input").each(function (index, element) {
        $("#studentManagementTbody").on("click", $(element), function () {
            $("#selectAll").prop("checked", false);
        });
    });
//多选删除
    $("#delSelected").on("click", function () {
        $("#studentManagementTbody").find("input:checked").each(function (index, element) {
            for (let i = 0; i < stuArr.length; i++) {
                if (stuArr[i].stuId === parseInt($(element).parents("tr").find(".hidden").eq(0).text())) {
                    stuArr.splice(i, 1);
                    break;
                }
            }
            if(isSearch){
                for (let i = 0; i < tempArr.length; i++) {
                    if (tempArr[i].stuId === parseInt($(element).parents("tr").find(".hidden").eq(0).text())) {
                        tempArr.splice(i, 1);
                        break;
                    }
                }
            }
            $(element).parents("tr").remove();
        });
        $("#selectAll").prop("checked", false);//去除全选选中状态
         pageUpdate(curPages)
    });
//搜索
    $("#search-btn").click(function(){
        let temArr=[];
        $(stuArr).each(function(i,item){
          if($("#search-type").val()==1) {
                if (item.stuName.match($("#search-input").val())) {
                    temArr.push(item)
                }
            }else if($("#search-type").val()==2) {
                if (item.className.match($("#search-input").val())) {
                    temArr.push(item)
                }
            }else if($("#search-type").val()==3){
                if (item.stuVIPState=='会员') {
                    temArr.push(item)
                }
            }else if($("#search-type").val()==4){
                if (item.stuVIPState=='非会员') {
                    temArr.push(item)
                }
            }
        });
        tempArr=temArr;
        isSearch=true;
        studentTableInforList(curPages);
        pageUpdate(curPages);
    });
//城市对应学校
    $("#cityNav>li>span").click(function(){
        let that=$(this);//保存this
        if(that.next().find("li").length==0){
            $(schoolArray).each(function(i,item){
                if(that.text()==item.cityName){
                    that.next().append(`<li data-isclick ><span class='iconfont icon-xuexiao'></span>${item.schName}</li>`)
                }
            });
        }
        //左侧菜单
        liFold();
        if ($(this).next().css("display") === "none") {
            $(this).parent().css("background-color", "#e6e6e6");
            $(this).parent().height(40 + $(this).next().height());
            $(this).children("span:last-of-type").css("-webkit-transform", "rotate(0)")
        } else {
            $(this).parent().css("background-color", "#f5f5f5");
            $(this).parent().height(40);
            $(this).children("span:last-of-type").css("-webkit-transform", "rotate(-90deg)");
        }
        $(this).next().slideToggle(200);
//学校对应学生
        $(".menu>li").click(function(){
            //改变标题
            let title=$(this).text();
            $("#student").text(`${title}学生一览`);
            //重置数据
            isSearch=false;curPages=0;
            //清空内容
            $("#studentManagementTbody").html("");//清空列表信息
            let that=this;
            //为false进来
            if(!$(that).prop("data-isclick")){
                //改变点击状态
                $(that).attr("data-isclick",true);
                //将其他状态变为false
                $(".menu>li").each(function(i,item){
                    if(item!=that){
                        $(item).attr("data-isclick",false);
                    }
                });
                //重置数组
                stuArr=[];
                $(studentArray).each(function(i,item){
                    if(item.schName==$(that).text()){
                        stuArr.push(item)
                    }
                });
            }
            //填充表格列表数据
            studentTableInforList(curPages);
            switcherStyle();
        })
    });
    //一级菜单收缩
    function liFold() {
        $("#cityNav>li>span").each(function (i, item) {
            $(item).parent().height(40);
            $(item).children("span:last-of-type").css("-webkit-transform", "rotate(-90deg)");
            $(item).parent().css("background-color", "#f5f5f5");
            if ($(item).parent().height() === 40) {
                $(item).next().css("display", "none");
            }
        });
    }
});
