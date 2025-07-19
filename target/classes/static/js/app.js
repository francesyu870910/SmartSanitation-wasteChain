// 智慧环卫管理系统前端应用
class SmartWasteApp {
    constructor() {
        this.baseUrl = '/api';
        this.currentPage = 'dashboard';
        this.init();
    }

    init() {
        this.showDashboard();
        this.startAutoRefresh();
    }

    // 显示仪表板
    async showDashboard() {
        this.setActivePage('dashboard', '系统仪表板');
        
        const content = `
            <div class="row mb-4">
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card stat-card">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="text-xs font-weight-bold text-uppercase mb-1">在线设备</div>
                                    <div class="h5 mb-0 font-weight-bold" id="online-devices">-</div>
                                </div>
                                <div class="col-auto">
                                    <i class="bi bi-cpu fa-2x"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card stat-card">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="text-xs font-weight-bold text-uppercase mb-1">活跃用户</div>
                                    <div class="h5 mb-0 font-weight-bold" id="active-users">-</div>
                                </div>
                                <div class="col-auto">
                                    <i class="bi bi-people fa-2x"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card stat-card">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="text-xs font-weight-bold text-uppercase mb-1">未解决报警</div>
                                    <div class="h5 mb-0 font-weight-bold" id="unresolved-alerts">-</div>
                                </div>
                                <div class="col-auto">
                                    <i class="bi bi-exclamation-triangle fa-2x"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card stat-card">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="text-xs font-weight-bold text-uppercase mb-1">今日投放</div>
                                    <div class="h5 mb-0 font-weight-bold" id="today-disposals">-</div>
                                </div>
                                <div class="col-auto">
                                    <i class="bi bi-recycle fa-2x"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-8">
                    <div class="card">
                        <div class="card-header">
                            <h6 class="m-0 font-weight-bold">最新报警</h6>
                        </div>
                        <div class="card-body">
                            <div id="recent-alerts">加载中...</div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="card">
                        <div class="card-header">
                            <h6 class="m-0 font-weight-bold">系统状态</h6>
                        </div>
                        <div class="card-body">
                            <div id="system-status">加载中...</div>
                        </div>
                    </div>
                </div>
            </div>
        `;
        
        document.getElementById('content-area').innerHTML = content;
        await this.loadDashboardData();
    }

    // 显示设备管理
    async showDevices() {
        this.setActivePage('devices', '设备管理');
        
        const content = `
            <div class="card">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>设备ID</th>
                                    <th>设备名称</th>
                                    <th>类型</th>
                                    <th>位置</th>
                                    <th>状态</th>
                                    <th>IP地址</th>
                                    <th>最后心跳</th>
                                </tr>
                            </thead>
                            <tbody id="devices-table">
                                <tr><td colspan="7" class="text-center">加载中...</td></tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        `;
        
        document.getElementById('content-area').innerHTML = content;
        await this.loadDevices();
    }

    // 显示垃圾桶监控
    async showContainers() {
        this.setActivePage('containers', '垃圾桶监控');
        
        const content = `
            <div class="row mb-3">
                <div class="col-md-4">
                    <select class="form-select" id="container-filter" onchange="app.filterContainers()">
                        <option value="">全部垃圾桶</option>
                        <option value="KITCHEN">厨余垃圾</option>
                        <option value="RECYCLABLE">可回收垃圾</option>
                        <option value="HAZARDOUS">有害垃圾</option>
                        <option value="OTHER">其他垃圾</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <select class="form-select" id="fullness-filter" onchange="app.filterContainers()">
                        <option value="">全部状态</option>
                        <option value="normal">正常 (<70%)</option>
                        <option value="warning">警告 (70-90%)</option>
                        <option value="critical">紧急 (>90%)</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <button class="btn btn-success" onclick="app.batchCheckFullness()">
                        <i class="bi bi-arrow-clockwise"></i> 批量检查满溢
                    </button>
                </div>
            </div>
            
            <div class="row" id="containers-grid">
                <div class="col-12 text-center">加载中...</div>
            </div>
        `;
        
        document.getElementById('content-area').innerHTML = content;
        await this.loadContainers();
    }

    // 显示报警管理
    async showAlerts() {
        this.setActivePage('alerts', '报警管理');
        
        const content = `
            <div class="card">
                <div class="card-body">
                    <div id="alerts-list">加载中...</div>
                </div>
            </div>
        `;
        
        document.getElementById('content-area').innerHTML = content;
        await this.loadAlerts();
    }

    // 显示用户管理
    async showUsers() {
        this.setActivePage('users', '用户管理');
        
        const content = `
            <div class="card">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>用户ID</th>
                                    <th>姓名</th>
                                    <th>手机号</th>
                                    <th>地址</th>
                                    <th>身份证</th>
                                    <th>状态</th>
                                </tr>
                            </thead>
                            <tbody id="users-table">
                                <tr><td colspan="6" class="text-center">加载中...</td></tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        `;
        
        document.getElementById('content-area').innerHTML = content;
        await this.loadUsers();
    }

    // 显示车辆管理
    async showVehicles() {
        this.setActivePage('vehicles', '车辆管理');
        
        const content = `
            <div class="card">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>车辆ID</th>
                                    <th>车牌号</th>
                                    <th>类型</th>
                                    <th>容量</th>
                                    <th>司机</th>
                                    <th>当前位置</th>
                                    <th>状态</th>
                                </tr>
                            </thead>
                            <tbody id="vehicles-table">
                                <tr><td colspan="7" class="text-center">加载中...</td></tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        `;
        
        document.getElementById('content-area').innerHTML = content;
        await this.loadVehicles();
    }

    // 显示积分管理
    async showPoints() {
        this.setActivePage('points', '积分管理');
        
        const content = `
            <div class="card">
                <div class="card-header">
                    <h6 class="m-0 font-weight-bold">积分排行榜</h6>
                </div>
                <div class="card-body">
                    <div id="points-ranking">加载中...</div>
                </div>
            </div>
        `;
        
        document.getElementById('content-area').innerHTML = content;
        await this.loadPointsData();
    }

    // 工具方法
    setActivePage(page, title) {
        this.currentPage = page;
        document.getElementById('page-title').textContent = title;
        
        // 更新导航状态
        document.querySelectorAll('.nav-link').forEach(link => {
            link.classList.remove('active');
        });
        document.querySelector(`[onclick="show${page.charAt(0).toUpperCase() + page.slice(1)}()"]`)?.classList.add('active');
    }

    async apiCall(endpoint, options = {}) {
        try {
            this.showLoading();
            const response = await fetch(this.baseUrl + endpoint, {
                headers: {
                    'Content-Type': 'application/json',
                    ...options.headers
                },
                ...options
            });
            
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            
            const data = await response.json();
            this.hideLoading();
            return data;
        } catch (error) {
            this.hideLoading();
            console.error('API调用失败:', error);
            this.showNotification('API调用失败: ' + error.message, 'error');
            return null;
        }
    }

    showLoading() {
        document.getElementById('loading-overlay').classList.remove('d-none');
    }

    hideLoading() {
        document.getElementById('loading-overlay').classList.add('d-none');
    }

    showNotification(message, type = 'info', duration = 3000) {
        const container = document.getElementById('notification-container');
        const alertClass = {
            'success': 'alert-success',
            'error': 'alert-danger',
            'warning': 'alert-warning',
            'info': 'alert-info'
        }[type] || 'alert-info';

        const notification = document.createElement('div');
        notification.className = `alert ${alertClass} alert-dismissible fade show fade-in`;
        notification.innerHTML = `
            <i class="bi bi-${this.getNotificationIcon(type)}"></i>
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        `;

        container.appendChild(notification);

        // 自动移除通知
        setTimeout(() => {
            if (notification.parentNode) {
                notification.remove();
            }
        }, duration);
    }

    getNotificationIcon(type) {
        const icons = {
            'success': 'check-circle',
            'error': 'exclamation-triangle',
            'warning': 'exclamation-triangle',
            'info': 'info-circle'
        };
        return icons[type] || 'info-circle';
    }

    // 数据加载方法
    async loadDashboardData() {
        try {
            // 加载统计数据
            const stats = await this.apiCall('/demo/dashboard/stats');
            if (stats) {
                document.getElementById('online-devices').textContent = stats.onlineDevices || '0';
                document.getElementById('active-users').textContent = stats.activeUsers || '0';
                document.getElementById('unresolved-alerts').textContent = stats.unresolvedAlerts || '0';
                document.getElementById('today-disposals').textContent = stats.todayDisposals || '0';
            }

            // 加载最新报警
            const alerts = await this.apiCall('/demo/dashboard/recent-alerts');
            if (alerts && alerts.length > 0) {
                document.getElementById('recent-alerts').innerHTML = alerts.slice(0, 3).map(alert => `
                    <div class="alert alert-${alert.alertLevel.toLowerCase()} mb-2">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <strong>${this.getAlertLevelText(alert.alertLevel)}</strong> - 
                                垃圾桶 ${alert.containerId} 满载率达到 ${(alert.fullnessLevel * 100).toFixed(1)}%
                                <small class="text-muted d-block">${this.formatTime(alert.alertTime)}</small>
                            </div>
                            ${!alert.isResolved ? 
                                `<button class="btn btn-sm btn-warning" onclick="app.resolveAlert('${alert.alertId}')">解决</button>` :
                                '<span class="badge bg-success">已解决</span>'
                            }
                        </div>
                    </div>
                `).join('');
            } else {
                document.getElementById('recent-alerts').innerHTML = '<div class="text-muted">暂无报警信息</div>';
            }
        } catch (error) {
            console.error('加载仪表板数据失败:', error);
        }
        
        // 加载系统状态
        document.getElementById('system-status').innerHTML = `
            <div class="mb-2">
                <span class="status-online">●</span> 数据库连接正常
            </div>
            <div class="mb-2">
                <span class="status-online">●</span> H2内存数据库
            </div>
            <div class="mb-2">
                <span class="status-online">●</span> 演示模式运行
            </div>
            <div>
                <span class="status-online">●</span> API服务正常
            </div>
        `;
    }

    async loadDevices() {
        const devices = await this.apiCall('/demo/devices');
        if (devices) {
            const tbody = document.getElementById('devices-table');
            tbody.innerHTML = devices.map(device => `
                <tr>
                    <td>${device.deviceId}</td>
                    <td>${device.deviceName}</td>
                    <td>${this.getDeviceTypeText(device.deviceType)}</td>
                    <td>${device.location}</td>
                    <td><span class="status-${device.status.toLowerCase()}">${this.getStatusText(device.status)}</span></td>
                    <td>${device.ipAddress || '-'}</td>
                    <td>${this.formatTime(device.lastHeartbeat)}</td>
                </tr>
            `).join('');
        }
    }

    async loadContainers() {
        const containers = await this.apiCall('/demo/containers');
        if (containers) {
            const grid = document.getElementById('containers-grid');
            grid.innerHTML = containers.map(container => {
                const status = container.currentFullness >= 0.9 ? 'critical' : 
                              container.currentFullness >= 0.7 ? 'warning' : 'normal';
                return `
                    <div class="col-lg-3 col-md-6 mb-4">
                        <div class="card ${status === 'critical' ? 'border-danger' : status === 'warning' ? 'border-warning' : 'border-success'}">
                            <div class="card-body">
                                <h6 class="card-title">${container.containerId}</h6>
                                <p class="card-text small text-muted">${container.location}</p>
                                <p class="card-text">类型: ${this.getWasteTypeText(container.wasteType)}</p>
                                <div class="progress mb-2">
                                    <div class="progress-bar ${status === 'critical' ? 'bg-danger' : status === 'warning' ? 'bg-warning' : 'bg-success'}" 
                                         style="width: ${container.currentFullness * 100}%"></div>
                                </div>
                                <small class="text-muted">满载率: ${(container.currentFullness * 100).toFixed(1)}%</small>
                            </div>
                        </div>
                    </div>
                `;
            }).join('');
        }
    }

    async loadAlerts() {
        const alerts = await this.apiCall('/demo/alerts');
        if (alerts) {
            const alertsList = document.getElementById('alerts-list');
            alertsList.innerHTML = alerts.map(alert => `
                <div class="alert alert-${alert.alertLevel.toLowerCase()} d-flex justify-content-between align-items-center">
                    <div>
                        <strong>${this.getAlertLevelText(alert.alertLevel)}</strong> - 
                        垃圾桶 ${alert.containerId} 满载率达到 ${(alert.fullnessLevel * 100).toFixed(1)}%
                        <small class="text-muted d-block">${this.formatTime(alert.alertTime)}</small>
                    </div>
                    <div>
                        ${alert.isResolved ? 
                            '<span class="badge bg-success">已解决</span>' : 
                            `<button class="btn btn-sm btn-warning" onclick="app.resolveAlert('${alert.alertId}')">解决</button>`
                        }
                    </div>
                </div>
            `).join('');
        }
    }

    async loadUsers() {
        const users = await this.apiCall('/demo/users');
        if (users) {
            const tbody = document.getElementById('users-table');
            tbody.innerHTML = users.map(user => `
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.name}</td>
                    <td>${user.phone}</td>
                    <td>${user.address}</td>
                    <td>${user.idCard || '-'}</td>
                    <td><span class="badge ${user.isActive ? 'bg-success' : 'bg-secondary'}">${user.isActive ? '活跃' : '停用'}</span></td>
                </tr>
            `).join('');
        }
    }

    async loadVehicles() {
        const vehicles = await this.apiCall('/demo/vehicles');
        if (vehicles) {
            const tbody = document.getElementById('vehicles-table');
            tbody.innerHTML = vehicles.map(vehicle => `
                <tr>
                    <td>${vehicle.vehicleId}</td>
                    <td>${vehicle.licensePlate}</td>
                    <td>${this.getWasteTypeText(vehicle.vehicleType)}</td>
                    <td>${vehicle.capacity}吨</td>
                    <td>${vehicle.driverId || '-'}</td>
                    <td>${vehicle.currentLocation || '-'}</td>
                    <td><span class="status-${vehicle.status.toLowerCase()}">${this.getVehicleStatusText(vehicle.status)}</span></td>
                </tr>
            `).join('');
        }
    }

    async loadPointsData() {
        const ranking = await this.apiCall('/demo/points/ranking');
        if (ranking) {
            const rankingDiv = document.getElementById('points-ranking');
            rankingDiv.innerHTML = ranking.map((user, index) => `
                <div class="d-flex justify-content-between align-items-center mb-2 p-2 ${index < 3 ? 'bg-light rounded' : ''}">
                    <div>
                        <span class="badge ${index === 0 ? 'bg-warning' : index === 1 ? 'bg-secondary' : index === 2 ? 'bg-info' : 'bg-light text-dark'}">${index + 1}</span>
                        <strong class="ms-2">用户 ${user.userId}</strong>
                        <small class="text-muted ms-2">${user.levelName}</small>
                    </div>
                    <div>
                        <span class="text-primary">${user.totalPoints} 分</span>
                    </div>
                </div>
            `).join('');
        }
    }

    // 解决报警
    async resolveAlert(alertId) {
        const result = await this.apiCall(`/demo/alerts/${alertId}/resolve`, { method: 'POST' });
        if (result) {
            this.showNotification('报警已成功解决！', 'success');
            if (this.currentPage === 'dashboard') {
                this.loadDashboardData();
            } else if (this.currentPage === 'alerts') {
                this.loadAlerts();
            }
        }
    }

    // 辅助方法
    getDeviceTypeText(type) {
        const types = {
            'SMART_BIN': '智能投放点',
            'FACE_RECOGNITION': '人脸识别',
            'QR_SCANNER': '二维码扫描',
            'IC_CARD_READER': 'IC卡读取',
            'WEIGHT_SENSOR': '称重传感器',
            'GPS_TRACKER': 'GPS定位',
            'CAMERA': '摄像头',
            'FULLNESS_SENSOR': '满溢检测'
        };
        return types[type] || type;
    }

    getStatusText(status) {
        const statuses = {
            'ONLINE': '在线',
            'OFFLINE': '离线',
            'ERROR': '故障',
            'MAINTENANCE': '维护中',
            'PENDING': '待激活'
        };
        return statuses[status] || status;
    }

    getWasteTypeText(type) {
        const types = {
            'KITCHEN': '厨余垃圾',
            'RECYCLABLE': '可回收垃圾',
            'HAZARDOUS': '有害垃圾',
            'OTHER': '其他垃圾'
        };
        return types[type] || type;
    }

    getVehicleStatusText(status) {
        const statuses = {
            'IDLE': '空闲',
            'COLLECTING': '收运中',
            'TRANSPORTING': '运输中',
            'MAINTENANCE': '维护中',
            'OFFLINE': '离线'
        };
        return statuses[status] || status;
    }

    getAlertLevelText(level) {
        const levels = {
            'CRITICAL': '紧急报警',
            'HIGH': '高级报警',
            'MEDIUM': '中级报警',
            'LOW': '低级报警'
        };
        return levels[level] || level;
    }

    formatTime(timeString) {
        if (!timeString) return '-';
        const date = new Date(timeString);
        const now = new Date();
        const diff = now - date;
        const minutes = Math.floor(diff / 60000);
        
        if (minutes < 1) return '刚刚';
        if (minutes < 60) return `${minutes}分钟前`;
        if (minutes < 1440) return `${Math.floor(minutes / 60)}小时前`;
        return `${Math.floor(minutes / 1440)}天前`;
    }

    // 自动刷新
    startAutoRefresh() {
        setInterval(() => {
            if (this.currentPage === 'dashboard') {
                this.loadDashboardData();
            }
        }, 30000); // 30秒刷新一次
    }

    // 刷新数据
    async refreshData() {
        try {
            switch (this.currentPage) {
                case 'dashboard':
                    await this.loadDashboardData();
                    break;
                case 'devices':
                    await this.loadDevices();
                    break;
                case 'containers':
                    await this.loadContainers();
                    break;
                case 'alerts':
                    await this.loadAlerts();
                    break;
                case 'users':
                    await this.loadUsers();
                    break;
                case 'vehicles':
                    await this.loadVehicles();
                    break;
                case 'points':
                    await this.loadPointsData();
                    break;
            }
            this.showNotification('数据刷新成功', 'success');
        } catch (error) {
            this.showNotification('数据刷新失败', 'error');
        }
    }

    // 批量检查垃圾桶满溢状态
    async batchCheckFullness() {
        try {
            const result = await this.apiCall('/demo/containers/batch-check', { method: 'POST' });
            if (result) {
                this.showNotification(`批量检查完成，发现 ${result.newAlerts || 0} 个新报警`, 'info');
                if (this.currentPage === 'containers') {
                    await this.loadContainers();
                }
            }
        } catch (error) {
            this.showNotification('批量检查失败', 'error');
        }
    }

    // 模拟设备心跳更新
    async updateDeviceHeartbeat(deviceId) {
        try {
            const result = await this.apiCall(`/demo/devices/${deviceId}/heartbeat`, { method: 'POST' });
            if (result) {
                this.showNotification('设备心跳更新成功', 'success');
                if (this.currentPage === 'devices') {
                    await this.loadDevices();
                }
            }
        } catch (error) {
            this.showNotification('设备心跳更新失败', 'error');
        }
    }

    // 查看垃圾桶详情
    viewContainerDetails(containerId) {
        this.showNotification(`查看垃圾桶 ${containerId} 详情`, 'info');
        // 这里可以打开模态框显示详细信息
    }

    // 跟踪车辆
    trackVehicle(vehicleId) {
        this.showNotification(`开始跟踪车辆 ${vehicleId}`, 'info');
        // 这里可以打开地图显示车辆位置
    }
}

// 全局应用实例
const app = new SmartWasteApp();

// 全局函数，供HTML调用
function showDashboard() { app.showDashboard(); }
function showDevices() { app.showDevices(); }
function showContainers() { app.showContainers(); }
function showAlerts() { app.showAlerts(); }
function showUsers() { app.showUsers(); }
function showVehicles() { app.showVehicles(); }
function showPoints() { app.showPoints(); }
function refreshData() { app.refreshData(); }