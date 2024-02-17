<template>
    <div>


        <div style="margin: 10px 0">
            <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入名称"
                      v-model="username"></el-input>
            <el-input style="width: 200px" suffix-icon="el-icon-message" placeholder="请输入邮箱"
                      v-model="email"></el-input>
            <el-input style="width: 200px" suffix-icon="el-icon-position" placeholder="请输入地址"
                      v-model="address"></el-input>


            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button class="ml-5" type="warning" @click="reset">重置</el-button>
        </div>

        <div style="margin: 10px 0">
            <el-button type="primary" @click="handleAdd">新增<i class="el-icon-circle-plus-outline"></i></el-button>


            <el-popconfirm
                class="ml-5"
                confirm-button-text='好的'
                cancel-button-text='不用了'
                icon="el-icon-info"
                icon-color="red"
                title="你确定删除吗？"
                @confirm="delBatch">

                <el-button slot="reference" type="danger">批量删除<i class="el-icon-remove-outline"></i>
                </el-button>
            </el-popconfirm>

        </div>


        <el-table :data="tableData" border stripe:header-cell-name="headerBg" @selection-change="handleSelectionChange">
            <!--                           批量的按钮-->

            <el-table-column
                type="selection"
                width="55">
            </el-table-column>


            <el-table-column
                prop="name" label="名称">
            </el-table-column>
            <el-table-column
                prop="path" label="路径">
            </el-table-column>
            <el-table-column
                prop="icon" label="图标">
            </el-table-column>
            <el-table-column
                prop="description" label="描述" >
            </el-table-column>

            <el-table-column
                lable="操作" width="200" align="center">

                <template slot-scope="scope">
                    <el-button type="success" @click="handleEit(scope.row)">编辑</el-button>

                    <el-popconfirm
                        class="ml-5"
                        confirm-button-text='好的'
                        cancel-button-text='不用了'
                        icon="el-icon-info"
                        icon-color="red"
                        title="你确定删除吗？"
                        @confirm="del(scope.row.id)"
                    >
                        <el-button slot="reference" type="danger">删除</el-button>

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
        <!--       弹窗填写信息-->
        <el-dialog title="菜单信息" :visible.sync="dialogFormVisible" width="30%">
            <el-form label-width="80px" size="small">

                <el-form-item label="名称" :label-width="formLabelWidth">
                    <el-input v-model="form.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="路径" :label-width="formLabelWidth">
                    <el-input v-model="form.path" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="图标" :label-width="formLabelWidth">
                    <el-input v-model="form.icon" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="描述" :label-width="formLabelWidth">
                    <el-input v-model="form.description" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </div>
        </el-dialog>


    </div>


</template>

<script>
export default {

    data() {
        return {
            tableData: [],
            total: 0,
            pageNum: 1,
            pageSize: 5,
            name: "",
            form: {},
            collapseBtnClass: 'el-icon-s-fold',
            isCollapse: false,
            dialogFormVisible: false,
            multipleSelection: [],

        }
    },
    created() {
        //
        this.load()
    },
    methods: {
        load() {
            this.request.get("/menu/page", {
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
        handleAdd() {
            this.dialogFormVisible = true
            this.form = {}
        },
        save() {
            this.request.post("/menu", this.form).then(res => {
                if (res.data) {
                    this.$message.success("保存成功")
                    this.dialogFormVisible = false
                    this.load()
                } else {
                    this.$message.success("保存失败")
                }
            })

        },
        handleEit(row) {
            this.form = row
            this.dialogFormVisible = true

        },
        //删除
        del(id) {
            this.request.delete("/menu/" + id).then(res => {
                if (res.data) {
                    this.$message.success("删除成功")
                    this.load()
                } else {
                    this.$message.success("删除失败")
                }
            })
        },
        //批量删除
        delBatch() {
            let ids = this.multipleSelection.map(v => v.id)
            this.request.post("/menu/del/batch/", ids).then(res => {
                if (res.data) {
                    this.$message.success("批量删除成功")
                    this.load()
                } else {
                    this.$message.success("批量删除失败")
                }
            })
        },
        handleSelectionChange(val) {
            console.log(val)
            this.multipleSelection = val
        },
        handleExcelImport(){
            this.$message.success("导入成功")//弹窗
            this.load()
        }
    }
}
</script>


<style>
.headerBg {
    background-color: #ccc;
}
</style>
