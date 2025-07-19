// 智慧环卫管理系统前端应用
new Vue({
    el: '#app',
    data() {
        return {
            activeMenu: 'dashboard',
            
            // 统计数据
            statistics: {
                totalDisposals: 0,
                activeUsers: 0,
                onlineDevices: 0,
                collectionEfficiency: 0
            },
            
            // 告警数据
            alerts: [],
            
            // 用户管理
            users: [],
            userSearch: '',
            userPagination: {
                currentPage: 1,
                pageSize: 10,
                total: 0
            },
            addUserDialogVisible: false,
            newUser: {
                name: '',
                phone: '',
                address: '',
                idCard: ''
            },
            userRules: {
                name: [
                    { required: true, message: '请输入用户姓名', trigger: 'blur' }
                ],
                phone: [
                    { required: true, message: '请输入手机号', trigger: 'blur' },
                    { pattern: /^1[3-9]\d{9}$/, message: '请输入正确的手机号', trigger: 'blur' }
                ],
                address: [
                    { required: true, message: '请输入地址', trigger: 'blur' }
                ],
                idCard: [
                    { required: true, message: '请输入身份证号', trigger: 'blur' }
                ]
            },
            
            // 设备监控
            devices: [],
            deviceStats: {
                online: 0,
                offline: 0,
                maintenance: 0,
                uptime: 0
            }
        }
    },
    
    mounted() {
        this.loadDashboardData();
        this.loadUsers();
        this.loadDevices();
        
        // 定时刷新数据
        setInterval(() => {
            if (this.activeMenu === 'dashboard') {
                this.loadDashboardData();
            }
        }, 30000); // 30秒刷新一次
    },
    
    methods: {
        // 菜单选择
        handleMenuSelect(key) {
            this.activeMenu = key;
            
            // 根据选择的菜单加载对应数据
            switch (key) {
                case 'dashboard':
                    this.loadDashboardData();
                    break;
                case 'users':
                    this.loadUsers();
                    break;
                case 'devices':
                    this.loadDevices();
                    break;
            }
        },
        
        // 用户下拉菜单
        handleCommand(command) {
            if (command === 'logout') {
                this.$confirm('确认退出登录?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    // 执行退出登录
                    window.location.href = '/login';
                });
            }
        },
        
        // 加载仪表板数据
        async loadDashboardData() {
            try {
                // 加载统计数据
                const statsResponse = await fetch('/api/statistics/kpi');
                if (statsResponse.ok) {
                    this.statistics = await statsResponse.json();
                }
                
                // 加载告警数据
                const alertsResponse = await fetch('/api/statistics/alerts');
                if (alertsResponse.ok) {
                    this.alerts = await alertsResponse.json();
                }
                
                // 渲染图表
                this.$nextTick(() => {
                    this.renderCharts();
                });
                
            } catch (error) {
                console.error('加载仪表板数据失败:', error);
                this.$message.error('加载数据失败');
            }
        },
        
        // 渲染图表
        renderCharts() {
            // 投放量趋势图
            const disposalChart = echarts.init(document.getElementById('disposalChart'));
            const disposalOption = {
                title: {
                    text: '近7天投放量趋势'
                },
                tooltip: {
                    trigger: 'axis'
                },
                xAxis: {
                    type: 'category',
                    data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: [120, 132, 101, 134, 90, 230, 210],
                    type: 'line',
                    smooth: true
                }]
            };
            disposalChart.setOption(disposalOption);
            
            // 垃圾分类占比图
            const wasteTypeChart = echarts.init(document.getElementById('wasteTypeChart'));
            const wasteTypeOption = {
                title: {
                    text: '垃圾分类占比'
                },
                tooltip: {
                    trigger: 'item'
                },
                series: [{
                    type: 'pie',
                    radius: '50%',
                    data: [
                        { value: 35, name: '厨余垃圾' },
                        { value: 25, name: '可回收垃圾' },
                        { value: 30, name: '其他垃圾' },
                        { value: 10, name: '有害垃圾' }
                    ]
                }]
            };
            wasteTypeChart.setOption(wasteTypeOption);
        },
        
        // 加载用户数据
        async loadUsers() {
            try {
                const response = await fetch(`/api/users?page=${this.userPagination.currentPage}&size=${this.userPagination.pageSize}&search=${this.userSearch}`);
                if (response.ok) {
                    const data = await response.json();
                    this.users = data.content || [];
                    this.userPagination.total = data.totalElements || 0;
                }
            } catch (error) {
                console.error('加载用户数据失败:', error);
                this.$message.error('加载用户数据失败');
            }
        },
        
        // 搜索用户
        searchUsers() {
            this.userPagination.currentPage = 1;
            this.loadUsers();
        },
        
        // 分页处理
        handleSizeChange(val) {
            this.userPagination.pageSize = val;
            this.loadUsers();
        },
        
        handleCurrentChange(val) {
            this.userPagination.currentPage = val;
            this.loadUsers();
        },
        
        // 显示添加用户对话框
        showAddUserDialog() {
            this.addUserDialogVisible = true;
            this.newUser = {
                name: '',
                phone: '',
                address: '',
                idCard: ''
            };
        },
        
        // 添加用户
        addUser() {
            this.$refs.userForm.validate(async (valid) => {
                if (valid) {
                    try {
                        const response = await fetch('/api/users', {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify(this.newUser)
                        });
                        
                        if (response.ok) {
                            this.$message.success('用户添加成功');
                            this.addUserDialogVisible = false;
                            this.loadUsers();
                        } else {
                            this.$message.error('用户添加失败');
                        }
                    } catch (error) {
                        console.error('添加用户失败:', error);
                        this.$message.error('添加用户失败');
                    }
                }
            });
        },
        
        // 编辑用户
        editUser(user) {
            this.$message.info('编辑用户功能开发中...');
        },
        
        // 删除用户
        deleteUser(user) {
            this.$confirm(`确认删除用户 ${user.name}?`, '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(async () => {
                try {
                    const response = await fetch(`/api/users/${user.userId}`, {
                        method: 'DELETE'
                    });
                    
                    if (response.ok) {
                        this.$message.success('用户删除成功');
                        this.loadUsers();
                    } else {
                        this.$message.error('用户删除失败');
                    }
                } catch (error) {
                    console.error('删除用户失败:', error);
                    this.$message.error('删除用户失败');
                }
            });
        },
        
        // 加载设备数据
        async loadDevices() {
            try {
                // 加载设备列表
                const devicesResponse = await fetch('/api/devices');
                if (devicesResponse.ok) {
                    this.devices = await devicesResponse.json();
                }
                
                // 加载设备统计
                const statsResponse = await fetch('/api/statistics/devices');
                if (statsResponse.ok) {
                    this.deviceStats = await statsResponse.json();
                }
                
            } catch (error) {
                console.error('加载设备数据失败:', error);
                this.$message.error('加载设备数据失败');
            }
        },
        
        // 查看设备详情
        viewDeviceDetail(device) {
            this.$message.info('设备详情功能开发中...');
        },
        
        // 重启设备
        restartDevice(device) {
            this.$confirm(`确认重启设备 ${device.deviceId}?`, '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(async () => {
                try {
                    const response = await fetch(`/api/devices/${device.deviceId}/restart`, {
                        method: 'POST'
                    });
                    
                    if (response.ok) {
                        this.$message.success('设备重启指令已发送');
                    } else {
                        this.$message.error('设备重启失败');
                    }
                } catch (error) {
                    console.error('重启设备失败:', error);
                    this.$message.error('重启设备失败');
                }
            });
        },
        
        // 工具方法
        formatDateTime(dateTime) {
            if (!dateTime) return '-';
            const date = new Date(dateTime);
            return date.toLocaleString('zh-CN');
        },
        
        getAlertTagType(level) {
            switch (level) {
                case 'CRITICAL': return 'danger';
                case 'WARNING': return 'warning';
                case 'INFO': return 'info';
                default: return '';
            }
        },
        
        getAlertTypeName(type) {
            const typeNames = {
                'DEVICE_OFFLINE': '设备离线',
                'HIGH_VIOLATION_RATE': '违规率过高',
                'SYSTEM_ERROR': '系统错误'
            };
            return typeNames[type] || type;
        },
        
        getDeviceStatusType(status) {
            switch (status) {
                case 'ONLINE': return 'success';
                case 'OFFLINE': return 'danger';
                case 'MAINTENANCE': return 'warning';
                default: return 'info';
            }
        },
        
        getDeviceStatusName(status) {
            const statusNames = {
                'ONLINE': '在线',
                'OFFLINE': '离线',
                'MAINTENANCE': '维护中'
            };
            return statusNames[status] || status;
        }
    }
});