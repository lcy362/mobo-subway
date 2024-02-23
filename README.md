# 北京地铁小工具
北京地铁小工具: 输入一个起点，获取到其他所有站点的最少站数及路线详情、换乘数。

参考: https://lichuanyang.top/posts/13793

## 数据获取
初始化时解析高德地图的json数据，并存储在本地mapdb中，后续启动时直接加载mapdb数据。

## 寻路算法
dijkstra算法的简单实现，只考虑站数，未针对换乘数、换乘时间、站点距离等进行优化。

## 使用例子
参考测试用例

## Todo
1. 功能更强大的入口方法

## ChangeLog
### 2024/2/23 v2.0.0
代码全面重构
### 2020/7/24 v1.1.1
增加点对点寻路功能
### 2020/6/24 v1.1.0
增加按站点直线距离规划路径
### 2020.3.13 v1.0.1
增加springBoot支持
### 2018.3.23 v1.0.0
初始版本发布

# fox-subway
A helpful tool for Beijing subway. Enter a starting point, then get the minimum number of stops and route details to all other stations.

refer : https://lichuanyang.top/posts/13793

## DATA
The data is from Ali-Map, then stored in local mapDB.

## EXAMPLE
see tests

## TODO
1. get stations distance data

2. more powerful entrance method

## ChangeLog
### 2024/2/23 v2.0.0
refactor all codes 
### 2020/7/24 v1.1.1
add path runner
### 2020/6/24 v1.1.0
add router with direct distance
### 2020.3.13 v1.0.1
add springBoot support
### 2018.3.23 v1.0.0
initial version
