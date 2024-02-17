<template>
    <div>
        <div style="margin: 10px 0">
            <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入名称" v-model="name"></el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button class="ml-5" type="warning" @click="reset">重置</el-button>
        </div>

        <div style="margin: 10px 0">
            <el-upload
                action="http://localhost:8081/file/upload"
                style="display: inline-block"
                :show-file-list="false"
                :on-success="handleFileUploadSuccess">

                <el-button class="ml-5" type="primary">上传文件<i class="el-icon-bottom"></i></el-button>
            </el-upload>
            <el-popconfirm
                class="ml-5"
                confirm-button-text='好的'
                cancel-button-text='不用了'
                icon="el-icon-info"
                icon-color="red"
                title="你确定删除吗？"
                @confirm="delBatch">
                <el-button slot="reference" type="danger">批量删除<i class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm>
        </div>



        <el-table :data="tableData" border stripe:header-cell-name="headerBg" @selection-change="handleSelectionChange">
            <!--                           批量的按钮-->

            <el-table-column
                type="selection"
                width="55">
            </el-table-column>

            <el-table-column
                prop="id" label="ID">
            </el-table-column>
            <el-table-column
                prop="name" label="文件名称" >
            </el-table-column>
            <el-table-column
                prop="type" label="文件类型" >
            </el-table-column>
            <el-table-column
                prop="size" label="文件大小">
            </el-table-column>

            <el-table-column label="下载">
                <template slot-scope="scope">
                    <el-button type="primary" @click="download(scope.row.url)">下载</el-button>
                </template>
            </el-table-column>

            <el-table-column  label="启用">
                <template slot-scope="scope">
                    <el-switch v-model="scope.row.enable" active-color="#13ce66" inactive-color="#ccc"
                               @change="changeEnable(scope.row)"></el-switch>
                </template>



            </el-table-column>
            <el-table-column
                lable="操作" width="200" align="center">

                <template slot-scope="scope">
                    <!--                                 <el-button type="success" @click="del(scope.row.id)">删除</el-button>-->

                    <el-popconfirm
                        class="ml-5"
                        confirm-button-text='确定'
                        cancel-button-text='我再想想'
                        icon="el-icon-info"
                        icon-color="red"
                        title="你确定删除吗？"
                        @confirm="del(scope.row.id)">
                        <el-button slot="reference" type="danger">删除<i class="el-icon-remove-outline"></i></el-button>
                    </el-popconfirm>

                </template>
            </el-table-column>
        </el-table>

        <div style="padding: 10px 0">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[2, 5, 10, 20]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>


        </div>



    </div>
</template>

<script>
export default {


    data(){
        return{
            tableData:[],
            name:'',
            multipleSelection:[],
            pageNum: 1,
            pageSize: 5,
            total:0,

        }
    },
    created() {
        //
        this.load()
    },
    methods: {
        load() {
            this.request.get("/file/page", {
                params: {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    name: this.name,

                }
            })
                .then(res => {
                    console.log(res)

                    this.tableData = res.data.records
                    this.total = res.data.total


                })
        },
        reset() {
            this.name = ""
            this.load()

        },
        handleSizeChange(pageSize) {
            console.log(pageSize)
            this.pageSize = pageSize;
            this.load()
        },
        handleCurrentChange(pageNum) {
            console.log(pageNum)
            this.pageNum = pageNum;
            this.load()
        },
        //删除
        del(id) {
            this.request.delete("/file/" + id).then(res => {
                if (res.code==='200') {
                    this.$message.success("删除成功")
                    this.load()
                } else {
                    this.$message.success("删除失败")
                    this.load()
                }
            })
        },
        //批量删除
        delBatch() {
            let ids = this.multipleSelection.map(v => v.id)
            this.request.post("/file/del/batch/", ids).then(res => {
                if (res.data) {
                    this.$message.success("批量删除成功")
                    this.load()
                } else {
                    this.$message.success("批量删除失败")
                    this.load()
                }
            })
        },
        handleFileUploadSuccess(res){
            console.log(res)
            this.load()
        },
        download(url){
            window.open(url)
        },
        handleSelectionChange(val) {
            console.log(val)
            this.multipleSelection = val
        },
        changeEnable(row){
            this.request.post("/file/update/", row).then(res=>{
                if(res.code==='200') {
                    this.$message.success("操作成功")
                }
            })
        }

    }

}
</script>


<style scoped>

</style>