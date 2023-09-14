<template>
  <div class="app-container">
<!-- 排班功能前端，最开始的代码实现 -->
    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="开始时间" prop="startTime">
        <el-date-picker v-model="queryParams.startTime" style="width: 300px" format="yyyy-MM-dd HH:mm" type="datetimerange" value-format="yyyy-MM-dd HH:mm:ss"
                         range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" />
      </el-form-item>
      <el-form-item label="结束时间" prop="endTime">
        <el-date-picker v-model="queryParams.endTime" style="width: 300px" format="yyyy-MM-dd HH:mm" type="datetimerange" value-format="yyyy-MM-dd HH:mm:ss"
                         range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" />
      </el-form-item>
      <el-form-item label="组织" prop="deptId">
        <el-select v-model="queryParams.deptId" placeholder="请选择组织" clearable size="small">
          <el-option v-for="item in deptOptions" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="值班类型" prop="workforceType">
            <el-select v-model="queryParams.workforceType" placeholder="请选择值班类型" style="width:100%" clearable>
                <el-option v-for="dict in this.getDictDatas(DICT_TYPE.DUTY_TYPE)"
                          :key="dict.value" :label="dict.label" :value="dict.value" />
            </el-select>
      </el-form-item>
      <el-form-item label="值班班长" prop="monitor">
        <!-- <el-input v-model="queryParams.monitor" placeholder="请输入值班班长" clearable @keyup.enter.native="handleQuery"/> -->
        <el-select v-model="queryParams.monitor" placeholder="值班班长" clearable style="width: 100%" >
          <!-- value要绑定id 否则不能识别相同昵称的用户 -->
          <el-option v-for="item in users" :key="item.id" :label="item.nickname" :value="item.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="值班人员" prop="dutyUser">
        <el-input v-model="queryParams.dutyUser" placeholder="请输入值班人员" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <!-- <el-form-item label="标注颜色" prop="color">
        <el-input v-model="queryParams.color" placeholder="请输入标注颜色" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item> -->
      <!-- <el-form-item label="备注信息" prop="remark">
        <el-input v-model="queryParams.remark" placeholder="请输入备注信息" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item> -->
      <!-- <el-form-item label="创建时间" prop="createTime">
        <el-date-picker v-model="queryParams.createTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['busine:work-force:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['busine:work-force:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" stripe :data="list">
      <!-- <el-table-column label="ID" align="center" prop="id" /> -->
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column label="序号" align="center" type="index" />
      <el-table-column label="组织" sortable show-overflow-tooltip  align="center" prop="deptId">
          <template slot-scope="scope">
             <span v-for="item in deptOptions" value="item" v-if="scope.row.deptId==item.id">
                 {{ item.name }}
             </span>
          </template>
      </el-table-column>
      <el-table-column label="值班班长" sortable show-overflow-tooltip align="center" prop="monitor" :formatter="userNicknameFormat"/>
      <!-- :formatter="userNicknameFormat"  -->
      <el-table-column label="值班人员" align="center" prop="dutyUser"/>
      <el-table-column label="值班类型" sortable show-overflow-tooltip align="center" prop="workforceType" >
           <template v-slot="scope">
                {{getDictDataLabel(DICT_TYPE.DUTY_TYPE, scope.row.workforceType)}}
           </template>
      </el-table-column>
      <el-table-column label="开始时间" sortable align="center" prop="startTime">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.startTime,'{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" sortable align="center" prop="endTime" >
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.endTime,'{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="标注颜色" sortable show-overflow-tooltip align="center" prop="color" /> -->
      <el-table-column label="标注颜色" sortable show-overflow-tooltip prop="color" >
        <template slot-scope="scope">
          <div :style="{ backgroundColor: scope.row.color, width: '40px', height: '25px' } "></div>
        </template>
      </el-table-column>
      <el-table-column label="备注信息" sortable show-overflow-tooltip align="center" prop="remark" />
      <!-- <el-table-column label="创建时间" sortable show-overflow-tooltip align="center" prop="createTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.createTime,'{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column> -->
      <el-table-column width="120" fixed="right" label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['busine:work-force:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['busine:work-force:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
             <el-form-item label="开始时间" prop="startTime">
            <!-- <el-date-picker clearable v-model="form.startTime" type="date" value-format="timestamp" placeholder="选择开始时间" style="width:100%" /> -->
                <el-date-picker  v-model="form.startTime" type="datetime" format="yyyy-MM-dd HH:mm" value-format="timestamp" placeholder="选择开始时间" style="width:100%" />
             </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="结束时间" prop="endTime">
            <!-- <el-date-picker clearable v-model="form.endTime" type="date" value-format="timestamp" placeholder="选择结束时间" style="width:100%" /> -->
                <el-date-picker  v-model="form.endTime" type="datetime" format="yyyy-MM-dd HH:mm" value-format="timestamp" placeholder="选择结束时间" style="width:100%" />
            </el-form-item>
          </el-col>
       </el-row>
       <el-row>
          <el-col :span="12">
            <el-form-item label="组织" prop="deptId">
            <!-- <el-input v-model="form.deptId" placeholder="请输入组织ID" /> -->
              <treeselect v-model="form.deptId" :options="deptTreeOptions" :show-count="true" :clearable="false"
                           placeholder="请选择组织" :normalizer="normalizer"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="值班类型" prop="workforceType">
              <el-select v-model="form.workforceType" placeholder="请选择值班类型" style="width:100%" clearable>
                <el-option v-for="dict in this.getDictDatas(DICT_TYPE.DUTY_TYPE)"
                          :key="dict.value" :label="dict.label" :value="dict.value" />
              </el-select>
            </el-form-item>
          </el-col>
       </el-row>
       <el-row>
          <el-col :span="12">
             <el-form-item label="值班班长" prop="monitor">
               <!-- <el-input v-model="form.monitor" placeholder="请输入值班班长" /> -->
                <el-select v-model="form.monitor" placeholder="请输入值班班长" clearable style="width: 100%">
                   <el-option v-for="item in users" :key="item.id" :label="item.nickname" :value="item.id" />
                </el-select>
                <!-- <el-input v-model="keyWord" placeholder="请输入值班班长" type="text">
                    <el-option v-for="(user,index) in filterUsers" :key="index">
                                 {{user.nickname}}
                    </el-option>
                </el-input> -->
             </el-form-item>
          </el-col>
          <el-col :span="12">
             <el-form-item label="值班人员" prop="dutyUser">
               <el-input v-model="form.dutyUser" placeholder="请输入值班人员" />
             </el-form-item>
          </el-col>
       </el-row>
       <el-row>
          <el-col :span="12">
            <el-form-item label="标注颜色" prop="color">
              <el-color-picker v-model="form.color"></el-color-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="备注信息" prop="remark">
              <el-input type="textarea" v-model="form.remark" placeholder="请输入备注信息" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { createWorkForce, updateWorkForce, deleteWorkForce, getWorkForce, getWorkForcePage, exportWorkForceExcel } from "@/api/busine/workForce";
import {listSimpleDepts} from "@/api/system/dept";
import {listSimpleUsers} from "@/api/system/user";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "WorkForce",
  components: {
    Treeselect
  },
  data() {
    return {
      deptOptions: [],
      deptTreeOptions:[],
      // 用户列表
      users: [],
      // keyWord: '',
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 排班列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        startTime: [],
        endTime: [],
        deptId: null,
        workforceType: null,
        monitor: null,
        dutyUser: null,
        color: null,
        remark: null,
        createTime: [],
      },
      // 表单参数
      form: {
        color: '',
      },
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getTreeselect();
    // 获得用户列表
    listSimpleUsers().then(response => {
      this.users = response.data;
    });
  },
  methods: {
    // 用户昵称展示
    userNicknameFormat(row, column) {
      if (!row.monitor) {
        return '未设置';
      }
      for (const user of this.users) {
        if (row.monitor === user.id) {
          return user.nickname;
        }
      }
      return '未知【' + row.monitor + '】';
    },
    /** 查询部门下拉树结构*/
    getTreeselect() {
      listSimpleDepts().then(response => {
        // 处理 deptOptions 参数
        this.deptOptions = [];
        this.deptTreeOptions = [];
        this.deptOptions.push(...response.data);
        this.deptTreeOptions.push(...this.handleTree(response.data, "id"));
      });
    },
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      getWorkForcePage(this.queryParams).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        startTime: undefined,
        endTime: undefined,
        deptId: undefined,
        workforceType: undefined,
        monitor: undefined,
        dutyUser: undefined,
        color: undefined,
        remark: undefined,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加排班";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getWorkForce(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改排班";
      });
    },
    /** 提交按钮 */
    submitForm() {
      console.log("this.form.color",this.form.color,this.form.deptId)
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateWorkForce(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createWorkForce(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除排班编号为"' + id + '"的数据项?').then(function() {
          return deleteWorkForce(id);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.$modal.confirm('是否确认导出所有排班数据项?').then(() => {
          this.exportLoading = true;
          return exportWorkForceExcel(params);
        }).then(response => {
          this.$download.excel(response, '排班.xls');
          this.exportLoading = false;
        }).catch(() => {});
    },
    // 格式的下拉框树
    normalizer(node) {
      return {
        id: node.id,
        label: node.name,
        children: node.children
      };
    },
  },
  // computed: {
  //   filterUsers(){
  //     return this.users.filter((user)=>{//返回过滤后的数组
  //                   return user.nickname.indexOf(this.keyWord) !==-1
  //               })//filter是过滤函数去除了不包含关键字的情况
  //   }
  // }
};
</script>
