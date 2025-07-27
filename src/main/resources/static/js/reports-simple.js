// 简化版报表应用
console.log('开始加载简化版报表应用...');

class SimpleReportApp {
    constructor() {
        this.currentPage = 'dashboard';
        console.log('SimpleReportApp 构造函数执行');
        this.init();
    }

    init() {
        console.log('初始化简化版报表应用');
        // 延迟执行以确保DOM加载完成
        setTimeout(() => {
            this.showDashboard();
        }, 100);
    }

    showDashboard() {
        console.log('显示概览页面');
        const contentArea = document.getElementById('content-area');
        if (!contentArea) {
            console.error('找不到content-area元素');
            return;
        }

        contentArea.innerHTML = `
            <div class="row">
                <div class="col-md-3">
                    <div class="card text-center">
                        <div class="card-body">
                            <h2 class="text-primary">156</h2>
                            <p>总报表数</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card text-center">
                        <div class="card-body">
                            <h2 class="text-success">12</h2>
                            <p>今日生成</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card text-center">
                        <div class="card-body">
                            <h2 class="text-info">2.3秒</h2>
                            <p>平均生成时间</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card text-center">
                        <div class="card-body">
                            <h2 class="text-warning">94.5%</h2>
                            <p>缓存命中率</p>
                        </div>
                    </div>
                </div>
            </div>
        `;
        
        this.updatePageTitle('报表概览');
    }

    showStandardReports() {
        console.log('显示标准报表页面');
        const contentArea = document.getElementById('content-area');
        if (!contentArea) {
            console.error('找不到content-area元素');
            return;
        }

        contentArea.innerHTML = `
            <div class="row mb-3">
                <div class="col-md-3">
                    <select class="form-select" id="report-type">
                        <option value="">选择报表类型</option>
                        <option value="收运">收运报表</option>
                        <option value="投放">投放报表</option>
                        <option value="处置">处置报表</option>
                        <option value="综合">综合报表</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <button class="btn btn-primary" onclick="simpleReportApp.generateReport()">生成报表</button>
                </div>
            </div>
            <div id="report-result">
                <p class="text-muted">请选择报表类型并点击生成报表</p>
            </div>
        `;
        
        this.updatePageTitle('标准报表');
    }

    showReportTemplates() {
        console.log('显示报表模板页面');
        const contentArea = document.getElementById('content-area');
        if (!contentArea) {
            console.error('找不到content-area元素');
            return;
        }

        const templates = [
            { id: 1, name: '收运效率分析', description: '分析收运车辆效率和路线优化情况', category: '收运' },
            { id: 2, name: '投放行为分析', description: '分析用户投放行为和分类准确性', category: '投放' },
            { id: 3, name: '环保指标监控', description: '监控处置环节的环保指标变化', category: '处置' },
            { id: 4, name: '综合运营报表', description: '全链条运营数据综合分析', category: '综合' }
        ];

        contentArea.innerHTML = `
            <div class="row">
                ${templates.map(template => `
                    <div class="col-md-6 mb-3">
                        <div class="card">
                            <div class="card-body">
                                <h6 class="card-title">${template.name}</h6>
                                <p class="card-text small text-muted">${template.description}</p>
                                <span class="badge bg-secondary">${template.category}</span>
                                <button class="btn btn-sm btn-primary float-end" onclick="simpleReportApp.useTemplate(${template.id})">
                                    使用模板
                                </button>
                            </div>
                        </div>
                    </div>
                `).join('')}
            </div>
        `;
        
        this.updatePageTitle('报表模板');
    }

    showTrendAnalysis() {
        console.log('显示趋势分析页面');
        const contentArea = document.getElementById('content-area');
        if (!contentArea) return;

        contentArea.innerHTML = `
            <div class="row mb-3">
                <div class="col-md-4">
                    <select class="form-select" id="trend-metric">
                        <option value="收运量">收运量</option>
                        <option value="回收率">回收率</option>
                        <option value="用户参与度">用户参与度</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <input type="date" class="form-control" id="start-date" value="${new Date(Date.now() - 30*24*60*60*1000).toISOString().split('T')[0]}">
                </div>
                <div class="col-md-4">
                    <button class="btn btn-primary" onclick="simpleReportApp.generateTrend()">分析趋势</button>
                </div>
            </div>
            <div id="trend-result">
                <p class="text-muted">请选择指标并点击分析趋势</p>
            </div>
        `;
        
        this.updatePageTitle('趋势分析');
    }

    showCustomReports() {
        console.log('显示自定义报表页面');
        const contentArea = document.getElementById('content-area');
        if (!contentArea) return;

        contentArea.innerHTML = `
            <div class="row mb-3">
                <div class="col-md-8">
                    <input type="text" class="form-control" placeholder="搜索报表名称...">
                </div>
                <div class="col-md-4">
                    <button class="btn btn-primary" onclick="simpleReportApp.showCreateModal()">创建报表</button>
                </div>
            </div>
            <div class="text-center">
                <p class="text-muted">暂无自定义报表，点击"创建报表"开始创建</p>
            </div>
        `;
        
        this.updatePageTitle('自定义报表');
    }

    showReportHistory() {
        console.log('显示历史记录页面');
        const contentArea = document.getElementById('content-area');
        if (!contentArea) return;

        contentArea.innerHTML = `
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>报表ID</th>
                            <th>类型</th>
                            <th>标题</th>
                            <th>生成时间</th>
                            <th>状态</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>RPT001</td>
                            <td><span class="badge bg-primary">收运报表</span></td>
                            <td>收运日报 - 2025-01-15</td>
                            <td>2025-01-15 09:30:00</td>
                            <td><span class="badge bg-success">已完成</span></td>
                        </tr>
                        <tr>
                            <td>RPT002</td>
                            <td><span class="badge bg-info">投放报表</span></td>
                            <td>投放月报 - 2024-12</td>
                            <td>2025-01-01 10:15:00</td>
                            <td><span class="badge bg-success">已完成</span></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        `;
        
        this.updatePageTitle('历史记录');
    }

    generateReport() {
        const reportType = document.getElementById('report-type').value;
        if (!reportType) {
            alert('请选择报表类型');
            return;
        }

        const data = {
            '收运': { '总收运量': '2,847吨', '完成率': '92.5%', '准时率': '89.2%', '车辆利用率': '78.5%' },
            '投放': { '总投放量': '5,234次', '用户参与度': '76.8%', '分类准确率': '82.1%', '积分发放量': '12,456分' },
            '处置': { '总处置量': '1,856吨', '回收率': '68.4%', '有害垃圾比例': '3.2%', '能源消耗': '2,145千瓦时' },
            '综合': { '总收运量': '2,847吨', '总投放量': '5,234次', '总处置量': '1,856吨', '回收率': '68.4%' }
        };

        const reportData = data[reportType];
        const resultDiv = document.getElementById('report-result');
        
        resultDiv.innerHTML = `
            <div class="card">
                <div class="card-header">
                    <h5>${reportType}报表</h5>
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
                </div>
            </div>
        `;
    }

    generateTrend() {
        const metric = document.getElementById('trend-metric').value;
        const resultDiv = document.getElementById('trend-result');
        
        resultDiv.innerHTML = `
            <div class="card">
                <div class="card-header">
                    <h5>趋势分析 - ${metric}</h5>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="text-center p-3 bg-light rounded">
                                <h4 class="text-success">+12.5%</h4>
                                <small class="text-muted">变化率</small>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="text-center p-3 bg-light rounded">
                                <h4 class="text-info">2,456</h4>
                                <small class="text-muted">平均值</small>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="text-center p-3 bg-light rounded">
                                <h4 class="text-success">3,124</h4>
                                <small class="text-muted">最大值</small>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="text-center p-3 bg-light rounded">
                                <h4 class="text-warning">1,856</h4>
                                <small class="text-muted">最小值</small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        `;
    }

    useTemplate(templateId) {
        const templates = {
            1: '收运', 2: '投放', 3: '处置', 4: '综合'
        };
        
        alert(`使用模板创建${templates[templateId]}报表成功！`);
        this.showStandardReports();
        
        setTimeout(() => {
            const select = document.getElementById('report-type');
            if (select) {
                select.value = templates[templateId];
                this.generateReport();
            }
        }, 100);
    }

    showCreateModal() {
        alert('创建自定义报表功能开发中');
    }

    updatePageTitle(title) {
        const titleElement = document.getElementById('page-title');
        if (titleElement) {
            titleElement.textContent = title;
        }
    }
}

// 全局变量
let simpleReportApp;

// 页面加载完成后初始化
document.addEventListener('DOMContentLoaded', function() {
    console.log('DOM加载完成，初始化简化版报表应用');
    try {
        simpleReportApp = new SimpleReportApp();
        console.log('简化版报表应用初始化成功');
    } catch (e) {
        console.error('简化版报表应用初始化失败:', e);
    }
});

// 导出全局函数
window.showDashboard = function() {
    console.log('全局函数 showDashboard 被调用');
    if (simpleReportApp) simpleReportApp.showDashboard();
};

window.showStandardReports = function() {
    console.log('全局函数 showStandardReports 被调用');
    if (simpleReportApp) simpleReportApp.showStandardReports();
};

window.showTrendAnalysis = function() {
    console.log('全局函数 showTrendAnalysis 被调用');
    if (simpleReportApp) simpleReportApp.showTrendAnalysis();
};

window.showCustomReports = function() {
    console.log('全局函数 showCustomReports 被调用');
    if (simpleReportApp) simpleReportApp.showCustomReports();
};

window.showReportTemplates = function() {
    console.log('全局函数 showReportTemplates 被调用');
    if (simpleReportApp) simpleReportApp.showReportTemplates();
};

window.showReportHistory = function() {
    console.log('全局函数 showReportHistory 被调用');
    if (simpleReportApp) simpleReportApp.showReportHistory();
};

window.refreshData = function() {
    console.log('全局函数 refreshData 被调用');
    if (simpleReportApp) simpleReportApp.showDashboard();
};

window.showCreateReportModal = function() {
    console.log('全局函数 showCreateReportModal 被调用');
    if (simpleReportApp) simpleReportApp.showCreateModal();
};

console.log('reports-simple.js 加载完成');