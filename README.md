# FoxSubway
北京地铁小工具: 输入一个起点，获取到其他所有站点的最少站数及路线详情、换乘数。

## 数据获取
初始化时解析高德地图的json数据，并存储在本地mapdb中，后续启动时直接加载mapdb数据。

## 寻路算法
dijkstra算法的简单实现，只考虑站数，未针对换乘数、换乘时间、站点距离等进行优化。

## 使用例子
```java
Map<String, PathInfo> aolin = Dijkstra.pathToAll("奥林匹克公园", stations);
        Map<String, PathInfo> tian = Dijkstra.pathToAll("天安门东", stations);
        for (Station station : stations.values()) {
            PathInfo aolinPath = aolin.get(station.getId());
            PathInfo tianPath = tian.get(station.getId());
            if (aolinPath.getLength() < 15 && tianPath.getLength() < 7) {
                System.out.println(station.getName() + " " + aolinPath.getLength() + "," + aolinPath.getTransferNum()
                + "," + tianPath.getLength() + "," + tianPath.getTransferNum());
            }
        }
```
输出距奥林匹克公园站15站以内，距天安门东站7站以内的地铁站列表，以及相应最短路线的站数和换乘数。

## Todo
1. 换乘开销、站点距离数据获取，并优化寻路算法

2. 前端界面

## ChangeLog
### 2018.3.23 v1.0.0
初始版本发布