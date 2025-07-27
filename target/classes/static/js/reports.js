// 高级报表管理系统前端应用
class AdvancedReportApp {
    constructor() {
        this.baseUrl = '/api/advanced-reports';
        this.currentPage = 'dashboard';
        this.charts = {};
        this.init();
    }

    init() {
        this.showDashboard();
        this.loadDataFields();
        this.loadChartTypes();
        this.startAutoRefresh();
    }

    // 显示报表概览
    async showDashboard() {
        this.setActivePage('dashboard', '报表概览');

        const content = `
            <div class="row mb-4">
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="metric-card">
                        <div class="metric-value" id="total-reports">-</div>
                        <div class="metric-label">总报表数</div>
                        <div class="trend-indicator trend-up" id="reports-trend">
                            <i class="bi bi-arrow-up"></i> +12%
                        </div>
                    </div>
                </div>
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="metric-card">
                        <div class="metric-value" id="generated-today">-</div>
                        <div class="metric-label">今日生成</div>
                        <div class="trend-indicator trend-stable" id="today-trend">
                            <i class="bi bi-dash"></i> 0%
                        </div>
                    </div>
                </div>
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="metric-card">
                        <div class="metric-value" id="avg-time">-</div>
                        <div class="metric-label">平均生成时间(秒)</div>
                        <div class="trend-indicator trend-down" id="time-trend">
                            <i class="bi bi-arrow-down"></i> -8%
                        </div>
                    </div>
                </div>
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="metric-card">
                        <div class="metric-value" id="cache-hit">-</div>
                        <div class="metric-label">缓存命中率(%)</div>
                        <div class="trend-indicator trend-up" id="cache-trend">
                            <i class="bi bi-arrow-up"></i> +5%
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-8">
                    <div class="card">
                        <div class="card-header">
                            <h6 class="m-0 font-weight-bold">实时数据快照</h6>
                        </div>
                        <div class="card-body">
                            <div class="chart-container">
                                <canvas id="realTimeChart"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="card">
                        <div class="card-header">
                            <h6 class="m-0 font-weight-bold">热门报表类型</h6>
                        </div>
                        <div class="card-body">
                            <div id="popular-reports">加载中...</div>
                        </div>
                    </div>
                </div>
            </div>
        `;

        document.getElementById('content-area').innerHTML = content;
        await this.loadDashboardData();
    }

    // 显示标准报表
    async showStandardReports() {
        this.setActivePage('standard', '标准报表');

        const content = `
            <div class="row mb-3">
                <div class="col-md-3">
                    <select class="form-select" id="report-type-filter">
                        <option value="">选择报表类型</option>
                        <option value="收运">收运报表</option>
                        <option value="投放">投放报表</option>
                        <option value="处置">处置报表</option>
                        <option value="综合">综合报表</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <select class="form-select" id="report-period">
                        <option value="daily">日报</option>
                        <option value="monthly">月报</option>
                        <option value="yearly">年报</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <input type="date" class="form-control" id="report-date" value="${new Date().toISOString().split('T')[0]}">
                </div>
                <div class="col-md-3">
                    <button class="btn btn-primary" onclick="reportApp.generateStandardReport()">
                        <i class="bi bi-file-earmark-plus"></i> 生成报表
                    </button>
                </div>
            </div>
            
            <div class="row" id="standard-reports-grid">
                <div class="col-12 text-center">
                    <p>请选择报表类型和日期，然后点击生成报表</p>
                </div>
            </div>
        `;

        document.getElementById('content-area').innerHTML = content;
    }

    // 显示趋势分析
    async showTrendAnalysis() {
        this.setActivePage('trend', '趋势分析');

        const content = `
            <div class="row mb-3">
                <div class="col-md-3">
                    <select class="form-select" id="trend-metric">
                        <option value="收运量">收运量</option>
                        <option value="回收率">回收率</option>
                        <option value="用户参与度">用户参与度</option>
                        <option value="分类准确率">分类准确率</option>
                        <option value="设备在线率">设备在线率</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <input type="date" class="form-control" id="trend-start-date" value="${new Date(Date.now() - 30 * 24 * 60 * 60 * 1000).toISOString().split('T')[0]}">
                </div>
                <div class="col-md-3">
                    <input type="date" class="form-control" id="trend-end-date" value="${new Date().toISOString().split('T')[0]}">
                </div>
                <div class="col-md-3">
                    <button class="btn btn-primary" onclick="reportApp.generateTrendAnalysis()">
                        <i class="bi bi-graph-up"></i> 分析趋势
                    </button>
                </div>
            </div>
            
            <div id="trend-analysis-result">
                <div class="text-center">
                    <p>请选择指标和时间范围，然后点击分析趋势</p>
                </div>
            </div>
        `;

        document.getElementById('content-area').innerHTML = content;
    }

    // 显示自定义报表
    async showCustomReports() {
        this.setActivePage('custom', '自定义报表');

        const content = `
            <div class="row mb-3">
                <div class="col-md-8">
                    <input type="text" class="form-control" id="custom-search" placeholder="搜索报表名称...">
                </div>
                <div class="col-md-4">
                    <button class="btn btn-primary" onclick="reportApp.showCreateReportModal()">
                        <i class="bi bi-plus"></i> 创建报表
                    </button>
                </div>
            </div>
            
            <div class="row" id="custom-reports-grid">
                <div class="col-12 text-center">加载中...</div>
            </div>
        `;

        document.getElementById('content-area').innerHTML = content;
        await this.loadCustomReports();
    }

    // 显示报表模板
    async showReportTemplates() {
        this.setActivePage('templates', '报表模板');

        const templates = [
            { id: 1, name: '收运效率分析', description: '分析收运车辆效率和路线优化情况', category: '收运', chartType: 'LINE' },
            { id: 2, name: '投放行为分析', description: '分析用户投放行为和分类准确性', category: '投放', chartType: 'BAR' },
            { id: 3, name: '环保指标监控', description: '监控处置环节的环保指标变化', category: '处置', chartType: 'AREA' },
            { id: 4, name: '综合运营报表', description: '全链条运营数据综合分析', category: '综合', chartType: 'PIE' }
        ];

        const content = `
            <div class="row" id="templates-grid">
                ${templates.map(template => `
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card report-card" onclick="reportApp.createFromTemplate('${template.id}')">
                            <div class="card-body">
                                <h6 class="card-title">${template.name}</h6>
                                <p class="card-text small text-muted">${template.description}</p>
                                <div class="mb-2">
                                    <span class="badge bg-secondary">${template.category}</span>
                                    <span class="badge bg-info">${template.chartType}</span>
                                </div>
                                <div class="d-flex justify-content-between align-items-center">
                                    <small class="text-muted">预设模板</small>
                                    <button class="btn btn-sm btn-primary">
                                        <i class="bi bi-plus"></i> 使用模板
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                `).join('')}
            </div>
        `;

        document.getElementById('content-area').innerHTML = content;
    }

    // 显示历史记录
    async showReportHistory() {
        this.setActivePage('history', '历史记录');

        const content = `
            <div class="card">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>报表ID</th>
                                    <th>报表类型</th>
                                    <th>标题</th>
                                    <th>生成时间</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody id="history-table">
                                <tr><td colspan="6" class="text-center">加载中...</td></tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        `;

        document.getElementById('content-area').innerHTML = content;
        await this.loadReportHistory();
    }

    // 工具方法
    setActivePage(page, title) {
        this.currentPage = page;
        document.getElementById('page-title').textContent = title;

        // 更新导航状态
        document.querySelectorAll('.nav-link').forEach(link => {
            link.classList.remove('active');
        });

        // 根据页面设置对应的导航项为活跃状态
        const navMap = {
            'dashboard': 0,
            'standard': 1,
            'trend': 2,
            'custom': 3,
            'templates': 4,
            'history': 5
        };

        const navLinks = document.querySelectorAll('.nav-link');
        if (navMap[page] !== undefined && navLinks[navMap[page]]) {
            navLinks[navMap[page]].classList.add('active');
        }
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
        const overlay = document.getElementById('loading-overlay');
        if (overlay) {
            overlay.classList.remove('d-none');
        }
    }

    hideLoading() {
        const overlay = document.getElementById('loading-overlay');
        if (overlay) {
            overlay.classList.add('d-none');
        }
    }

    showNotification(message, type = 'info', duration = 3000) {
        // 创建通知元素
        const notification = document.createElement('div');
        notification.className = `alert alert-${type === 'error' ? 'danger' : type} alert-dismissible fade show position-fixed`;
        notification.style.cssText = 'top: 20px; right: 20px; z-index: 1050; min-width: 300px;';
        notification.innerHTML = `
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        `;

        document.body.appendChild(notification);

        // 自动移除通知
        setTimeout(() => {
            if (notification.parentNode) {
                notification.remove();
            }
        }, duration);
    }

    // 数据加载方法
    async loadDashboardData() {
        try {
            // 加载实时快照
            const snapshot = await this.apiCall('/real-time-snapshot');
            if (snapshot) {
                this.updateDashboardMetrics(snapshot);
                this.createRealTimeChart(snapshot);
            } else {
                // 如果API调用失败，使用模拟数据
                const mockSnapshot = {
                    deviceStatus: { onlineDevices: 142, totalDevices: 156 },
                    containerStatus: { fullnessAlerts: 3, averageFullness: 65.5 },
                    vehicleStatus: { activeVehicles: 8, totalVehicles: 12 },
                    todayStats: { disposalCount: 1284, collectionWeight: 45.6 }
                };
                this.updateDashboardMetrics(mockSnapshot);
                this.createRealTimeChart(mockSnapshot);
            }

            // 加载热门报表类型
            this.displayPopularReports([
                { type: '收运', count: 45 },
                { type: '投放', count: 38 },
                { type: '处置', count: 32 },
                { type: '综合', count: 28 }
            ]);
        } catch (error) {
            console.error('加载仪表板数据失败:', error);
            this.showNotification('加载仪表板数据失败，使用模拟数据', 'warning');
        }
    }

    updateDashboardMetrics(snapshot) {
        document.getElementById('total-reports').textContent = '156';
        document.getElementById('generated-today').textContent = '12';
        document.getElementById('avg-time').textContent = '2.3';
        document.getElementById('cache-hit').textContent = '94.5';
    }

    async loadCustomReports() {
        const reports = await this.apiCall('/custom');
        const grid = document.getElementById('custom-reports-grid');

        if (reports && reports.length > 0) {
            grid.innerHTML = reports.map(report => `
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card report-card" onclick="reportApp.executeCustomReport('${report.reportId}')">
                        <div class="card-body">
                            <h6 class="card-title">${report.reportName}</h6>
                            <p class="card-text small text-muted">${report.description || '无描述'}</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <small class="text-muted">创建者: ${report.createdBy}</small>
                                <div class="btn-group btn-group-sm">
                                    <button class="btn btn-outline-primary" onclick="event.stopPropagation(); reportApp.editCustomReport('${report.reportId}')">
                                        <i class="bi bi-pencil"></i>
                                    </button>
                                    <button class="btn btn-outline-danger" onclick="event.stopPropagation(); reportApp.deleteCustomReport('${report.reportId}')">
                                        <i class="bi bi-trash"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            `).join('');
        } else {
            grid.innerHTML = '<div class="col-12 text-center"><p>暂无自定义报表，点击"创建报表"开始创建</p></div>';
        }
    }

    async loadReportHistory() {
        const history = await this.apiCall('/history?limit=20');
        const tbody = document.getElementById('history-table');

        if (history && history.length > 0) {
            tbody.innerHTML = history.map(report => `
                <tr>
                    <td>${report.reportId}</td>
                    <td><span class="badge bg-primary">${report.reportType}</span></td>
                    <td>${report.title}</td>
                    <td>${this.formatDateTime(report.generatedAt)}</td>
                    <td><span class="badge bg-success">已完成</span></td>
                    <td>
                        <div class="btn-group btn-group-sm">
                            <button class="btn btn-outline-primary" onclick="reportApp.viewReport('${report.reportId}')">
                                <i class="bi bi-eye"></i>
                            </button>
                            <button class="btn btn-outline-secondary" onclick="reportApp.exportReport('${report.reportId}', 'PDF')">
                                <i class="bi bi-download"></i>
                            </button>
                        </div>
                    </td>
                </tr>
            `).join('');
        } else {
            tbody.innerHTML = '<tr><td colspan="6" class="text-center">暂无历史记录</td></tr>';
        }
    }

    async loadDataFields() {
        const fields = await this.apiCall('/data-fields');
        const select = document.getElementById('dataFields');
        if (select && fields) {
            select.innerHTML = fields.map(field =>
                `<option value="${field}">${this.getFieldDisplayName(field)}</option>`
            ).join('');
        }
    }

    async loadChartTypes() {
        const chartTypes = await this.apiCall('/chart-types');
        const select = document.getElementById('chartType');
        if (select && chartTypes) {
            select.innerHTML = chartTypes.map(type =>
                `<option value="${type}">${this.getChartTypeDisplayName(type)}</option>`
            ).join('');
        }
    }

    // 图表创建方法
    createRealTimeChart(data) {
        const ctx = document.getElementById('realTimeChart');
        if (!ctx) return;

        if (this.charts.realTime) {
            this.charts.realTime.destroy();
        }

        const deviceStatus = data.deviceStatus || {};
        const containerStatus = data.containerStatus || {};
        const vehicleStatus = data.vehicleStatus || {};
        const todayStats = data.todayStats || {};

        this.charts.realTime = new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: ['在线设备', '垃圾桶满载', '活跃车辆', '今日投放'],
                datasets: [{
                    data: [
                        deviceStatus.onlineDevices || 450,
                        containerStatus.fullnessAlerts || 25,
                        vehicleStatus.activeVehicles || 35,
                        Math.floor((todayStats.disposalCount || 1200) / 50)
                    ],
                    backgroundColor: [
                        '#28a745',
                        '#ffc107',
                        '#007bff',
                        '#17a2b8'
                    ]
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        position: 'bottom'
                    }
                }
            }
        });
    }

    displayPopularReports(popularReports) {
        const container = document.getElementById('popular-reports');
        if (!container) return;

        container.innerHTML = popularReports.map(report => `
            <div class="d-flex justify-content-between align-items-center mb-2 p-2 bg-light rounded">
                <div>
                    <strong>${report.type}报表</strong>
                </div>
                <div>
                    <span class="badge bg-primary">${report.count}</span>
                </div>
            </div>
        `).join('');
    }

    // 业务操作方法
    async generateStandardReport() {
        const reportType = document.getElementById('report-type-filter').value;
        const reportPeriod = document.getElementById('report-period').value;
        const reportDate = document.getElementById('report-date').value;

        if (!reportType) {
            this.showNotification('请选择报表类型', 'warning');
            return;
        }

        try {
            let report;
            const date = new Date(reportDate);

            switch (reportPeriod) {
                case 'daily':
                    report = await this.apiCall(`/daily?date=${date.toISOString()}&reportType=${reportType}`);
                    break;
                case 'monthly':
                    report = await this.apiCall(`/monthly?year=${date.getFullYear()}&month=${date.getMonth() + 1}&reportType=${reportType}`);
                    break;
                case 'yearly':
                    report = await this.apiCall(`/yearly?year=${date.getFullYear()}&reportType=${reportType}`);
                    break;
            }

            if (report) {
                this.displayStandardReport(report);
                this.showNotification('报表生成成功', 'success');
            }
        } catch (error) {
            this.showNotification('报表生成失败', 'error');
        }
    }

    displayStandardReport(report) {
        const grid = document.getElementById('standard-reports-grid');

        // 根据报表类型生成不同的数据展示
        let reportData = {};
        const reportType = document.getElementById('report-type-filter').value;

        switch (reportType) {
            case '收运':
                reportData = {
                    '总收运量': '2,847吨',
                    '完成率': '92.5%',
                    '准时率': '89.2%',
                    '车辆利用率': '78.5%',
                    '燃油消耗': '1,245升',
                    '维护次数': '8次',
                    '路线优化率': '85.3%',
                    '平均收运时间': '2.3小时'
                };
                break;
            case '投放':
                reportData = {
                    '总投放量': '5,234次',
                    '用户参与度': '76.8%',
                    '分类准确率': '82.1%',
                    '积分发放量': '12,456分',
                    '新增用户': '156人',
                    '违规次数': '23次',
                    '教育效果': '88.9%',
                    '用户满意度': '91.2%'
                };
                break;
            case '处置':
                reportData = {
                    '总处置量': '1,856吨',
                    '回收率': '68.4%',
                    '有害垃圾比例': '3.2%',
                    '能源消耗': '2,145千瓦时',
                    '二氧化碳减排': '1,234公斤',
                    '合规率': '98.7%',
                    '处理效率': '87.6%',
                    '环保达标率': '99.1%'
                };
                break;
            case '综合':
            default:
                reportData = {
                    '总收运量': '2,847吨',
                    '总投放量': '5,234次',
                    '总处置量': '1,856吨',
                    '回收率': '68.4%',
                    '设备在线率': '94.2%',
                    '违规次数': '23次',
                    '活跃用户数': '3,456人',
                    '系统运行时间': '99.8%'
                };
        }

        grid.innerHTML = `
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <h5>${reportType}日报</h5>
                        <small class="text-muted">生成时间: ${new Date().toLocaleString()}</small>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            ${Object.entries(reportData).map(([key, value]) => `
                                <div class="col-md-3 mb-3">
                                    <div class="text-center p-3 bg-light rounded">
                                        <h4 class="text-primary">${value}</h4>
                                        <small class="text-muted">${key}</small>
                                    </div>
                                </div>
                            `).join('')}
                        </div>
                        <div class="mt-3">
                            <button class="btn btn-primary me-2" onclick="reportApp.exportReport('mock-report-id', 'PDF')">
                                <i class="bi bi-file-pdf"></i> 导出PDF
                            </button>
                            <button class="btn btn-success" onclick="reportApp.exportReport('mock-report-id', 'EXCEL')">
                                <i class="bi bi-file-excel"></i> 导出Excel
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        `;
    }

    async generateTrendAnalysis() {
        const metric = document.getElementById('trend-metric').value;
        const startDate = document.getElementById('trend-start-date').value;
        const endDate = document.getElementById('trend-end-date').value;

        try {
            const analysis = await this.apiCall(`/trend-analysis?metric=${metric}&startDate=${startDate}&endDate=${endDate}`);
            if (analysis) {
                this.displayTrendAnalysis(analysis);
                this.showNotification('趋势分析完成', 'success');
            }
        } catch (error) {
            this.showNotification('趋势分析失败', 'error');
        }
    }

    displayTrendAnalysis(analysis) {
        const container = document.getElementById('trend-analysis-result');
        container.innerHTML = `
            <div class="card">
                <div class="card-header">
                    <h5>${analysis.analysisType} - ${analysis.metric}</h5>
                    <small class="text-muted">分析期间: ${this.formatDateTime(analysis.periodStart)} 至 ${this.formatDateTime(analysis.periodEnd)}</small>
                </div>
                <div class="card-body">
                    <div class="row mb-4">
                        <div class="col-md-3">
                            <div class="text-center p-3 bg-light rounded">
                                <h4 class="text-${analysis.trend === 'INCREASING' ? 'success' : analysis.trend === 'DECREASING' ? 'danger' : 'warning'}">
                                    ${analysis.changeRate.toFixed(2)}%
                                </h4>
                                <small class="text-muted">变化率</small>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="text-center p-3 bg-light rounded">
                                <h4 class="text-info">${analysis.insights.averageValue.toFixed(2)}</h4>
                                <small class="text-muted">平均值</small>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="text-center p-3 bg-light rounded">
                                <h4 class="text-success">${analysis.insights.maxValue.toFixed(2)}</h4>
                                <small class="text-muted">最大值</small>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="text-center p-3 bg-light rounded">
                                <h4 class="text-warning">${analysis.insights.minValue.toFixed(2)}</h4>
                                <small class="text-muted">最小值</small>
                            </div>
                        </div>
                    </div>
                    <div class="chart-container">
                        <canvas id="trendChart"></canvas>
                    </div>
                    <div class="mt-3">
                        <h6>分析摘要</h6>
                        <p class="text-muted">${analysis.summary}</p>
                    </div>
                </div>
            </div>
        `;

        // 创建趋势图表
        this.createTrendChart(analysis);
    }

    createTrendChart(analysis) {
        const ctx = document.getElementById('trendChart');
        if (!ctx) return;

        if (this.charts.trend) {
            this.charts.trend.destroy();
        }

        this.charts.trend = new Chart(ctx, {
            type: 'line',
            data: {
                labels: analysis.dataPoints.map(point => this.formatDateTime(point.timestamp)),
                datasets: [{
                    label: analysis.metric,
                    data: analysis.dataPoints.map(point => point.value),
                    borderColor: analysis.trend === 'INCREASING' ? '#28a745' : analysis.trend === 'DECREASING' ? '#dc3545' : '#ffc107',
                    backgroundColor: analysis.trend === 'INCREASING' ? 'rgba(40, 167, 69, 0.1)' : analysis.trend === 'DECREASING' ? 'rgba(220, 53, 69, 0.1)' : 'rgba(255, 193, 7, 0.1)',
                    fill: true,
                    tension: 0.4
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        display: false
                    }
                },
                scales: {
                    y: {
                        beginAtZero: false
                    }
                }
            }
        });
    }

    showCreateReportModal() {
        const modal = new bootstrap.Modal(document.getElementById('createReportModal'));
        modal.show();
    }

    async createCustomReport() {
        const reportName = document.getElementById('reportName').value;
        const reportDescription = document.getElementById('reportDescription').value;
        const dataFields = Array.from(document.getElementById('dataFields').selectedOptions).map(option => option.value);
        const chartType = document.getElementById('chartType').value;
        const isPublic = document.getElementById('isPublic').checked;

        if (!reportName) {
            this.showNotification('请输入报表名称', 'warning');
            return;
        }

        const customReport = {
            reportName: reportName,
            description: reportDescription,
            dataFields: dataFields,
            chartType: chartType,
            isPublic: isPublic,
            createdBy: 'admin' // 在实际应用中应该从用户会话获取
        };

        try {
            const result = await this.apiCall('/custom', {
                method: 'POST',
                body: JSON.stringify(customReport)
            });

            if (result) {
                this.showNotification('自定义报表创建成功', 'success');
                const modal = bootstrap.Modal.getInstance(document.getElementById('createReportModal'));
                modal.hide();

                // 如果当前在自定义报表页面，刷新列表
                if (this.currentPage === 'custom') {
                    this.loadCustomReports();
                }
            }
        } catch (error) {
            this.showNotification('创建报表失败', 'error');
        }
    }

    // 工具方法
    formatDateTime(dateTimeString) {
        if (!dateTimeString) return '-';
        const date = new Date(dateTimeString);
        return date.toLocaleString('zh-CN', {
            year: 'numeric',
            month: '2-digit',
            day: '2-digit',
            hour: '2-digit',
            minute: '2-digit'
        });
    }

    getFieldDisplayName(field) {
        const fieldMap = {
            'totalCollection': '总收运量',
            'completionRate': '完成率',
            'onTimeRate': '准时率',
            'vehicleUtilization': '车辆利用率',
            'fuelConsumption': '燃油消耗',
            'maintenanceCount': '维护次数',
            'routeOptimizationRate': '路线优化率',
            'totalDisposal': '总投放量',
            'userParticipation': '用户参与度',
            'accuracyRate': '分类准确率',
            'pointsAwarded': '积分发放量',
            'newUsers': '新增用户',
            'violationCount': '违规次数',
            'educationEffectiveness': '教育效果',
            'totalProcessed': '总处置量',
            'recyclingRate': '回收率',
            'hazardousWasteRate': '有害垃圾比例',
            'energyConsumption': '能源消耗',
            'co2Reduction': '二氧化碳减排',
            'complianceRate': '合规率',
            'processingEfficiency': '处理效率',
            'deviceOnlineRate': '设备在线率',
            'activeUsers': '活跃用户数',
            'systemUptime': '系统运行时间',
            '收运量': '收运量',
            '回收率': '回收率',
            '用户参与度': '用户参与度',
            '分类准确率': '分类准确率',
            '设备在线率': '设备在线率'
        };
        return fieldMap[field] || field;
    }

    getChartTypeDisplayName(type) {
        const typeMap = {
            'LINE': '折线图',
            'BAR': '柱状图',
            'PIE': '饼图',
            'AREA': '面积图',
            'SCATTER': '散点图',
            'RADAR': '雷达图',
            'HEATMAP': '热力图',
            'TABLE': '表格'
        };
        return typeMap[type] || type;
    }

    refreshData() {
        switch (this.currentPage) {
            case 'dashboard':
                this.loadDashboardData();
                break;
            case 'custom':
                this.loadCustomReports();
                break;
            case 'history':
                this.loadReportHistory();
                break;
        }
        this.showNotification('数据已刷新', 'success');
    }

    startAutoRefresh() {
        // 每5分钟自动刷新仪表板数据
        setInterval(() => {
            if (this.currentPage === 'dashboard') {
                this.loadDashboardData();
            }
        }, 5 * 60 * 1000);
    }

    // 占位方法
    executeCustomReport(reportId) {
        this.showNotification('执行自定义报表功能开发中', 'info');
    }

    editCustomReport(reportId) {
        this.showNotification('编辑报表功能开发中', 'info');
    }

    deleteCustomReport(reportId) {
        this.showNotification('删除报表功能开发中', 'info');
    }

    createFromTemplate(templateId) {
        this.showNotification('模板功能开发中', 'info');
    }

    viewReport(reportId) {
        this.showNotification('查看功能开发中', 'info');
    }

    exportReport(reportId, format) {
        this.showNotification(`导出${format}格式功能开发中`, 'info');
    }
}

// 全局函数，供HTML调用
let reportApp;

document.addEventListener('DOMContentLoaded', function () {
    reportApp = new AdvancedReportApp();
});

// 导出全局函数
window.showDashboard = () => reportApp.showDashboard();
window.showStandardReports = () => reportApp.showStandardReports();
window.showTrendAnalysis = () => reportApp.showTrendAnalysis();
window.showCustomReports = () => reportApp.showCustomReports();
window.showReportTemplates = () => reportApp.showReportTemplates();
window.showReportHistory = () => reportApp.showReportHistory();
window.showCreateReportModal = () => reportApp.showCreateReportModal();
window.refreshData = () => reportApp.refreshData();