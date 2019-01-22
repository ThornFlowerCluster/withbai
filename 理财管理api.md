**简要描述：** 

- 首页数据 理财 基金 

**请求URL：** 
- ` http://localhost:8080/api/v1/funds/category?power=？ `

**请求方式：**
- GET 

**参数：** 

| 参数名 | 必选 | 类型 | 说明               |
| :----- | :--- | :--- | ------------------ |
| power  | 是   | int  | 分类 0基金， 1理财 |


 **返回示例**


 ``` 
 {
  "status": 200,
  "message": "ok",
  "data": [
    {
      "fid": 37,
      "name": "国投瑞银基金",
      "interestRate": 0.8482,
      "baseline": 1000,
      "yearRate": 0.3306,
      "investTime": "",
      "number": 1000000,
      "positions": 0
    },
   ......
  ]
} 

 ```

 **返回参数说明** 

| 参数名       | 类型   | 说明         |
| :----------- | :----- | ------------ |
| status       | int    | 响应状态码   |
| fid          | Long   | 编号         |
| name         | String | 基金理财简称 |
| interestRate | Long   | 利率         |
| baseline     | Long   | 最低起购金额 |
| yearRate     | Long   | 年利率       |
| investTime   | String | 投资时限     |
| number       | Long   | 全部数量     |
| positions    | Long   | 已购买数量   |



------



**简要描述：** 

- 理财 基金 分页数据

**请求URL：** 
- ` http://localhost:8080/api/v1/funds/page `

**请求方式：**
- GET 

**参数：** 

| 参数名     | 必选 | 类型 | 说明                                                         |
| :--------- | :--- | :--- | ------------------------------------------------------------ |
| power      | 是   | int  | 分类 0基金， 1理财 默认为0                                   |
| page       | 是   | int  | 页码 默认为1                                                 |
| limit      | 否   | int  | 每页条数 默认为10                                            |
| investTime | 是   | int  | 投资时限，默认为 0 查询全部![](https://www.showdoc.cc/server/api/common/visitfile/sign/c1ae58205891d26160b17d6581e9a2ed?showdoc=.jpg) |


 **返回示例**
 ``` 
 {
  "status": 200,
  "message": "ok",
  "data": [
    {
      "limit": 10,
      "page": 1,
      "pagesNo": 11
    },
    {
      "fid": 1,
      "name": "安信基金",
      "interestRate": 0.9128,
      "baseline": 1000,
      "yearRate": 0.3702,
      "investTime": 1,
      "number": 1000000,
      "positions": 0
    }
  ]
}
 ```
 **返回参数说明** 

| 参数名       | 类型   | 说明         |
| :----------- | :----- | ------------ |
| status       | int    | 响应状态码   |
| pageNo       | int    | 当前页码     |
| limit        | int    | 每页条数     |
| pagesNo      | int    | 分类全部页数 |
| fid          | Long   | 编号         |
| name         | String | 基金理财简称 |
| interestRate | Long   | 利率         |
| baseline     | Long   | 最低起购金额 |
| yearRate     | Long   | 年利率       |
| investTime   | String | 投资时限     |
| number       | Long   | 全部数量     |
| positions    | Long   | 已购买数量   |



------



**简要描述：** 

- 产品详细信息

**请求URL：** 
- ` http://localhost:8080/api/v1/funds/fund?fid=? `

**请求方式：**
- GET 

**参数：** 

| 参数名 | 必选 | 类型   | 说明     |
| :----- | :--- | :----- | -------- |
| fid    | 是   | string | 产品序号 |

 **返回示例**

``` 
  {
    "status": 200,
    "message": "ok",
    "data": {
        "fid": 110,
        "name": "华泰柏瑞核心优势",
        "title": "",
        "interestRate": 0.8806,
        "time": 1547977802000,
        "baseline": 1000,
        "yearRate": 0.3306,
        "investTime": 2,
        "unitPrice": 2,
        "number": 1000000,
        "positions": 0,
        "introduction": "<P>dawdasncskvfnergidsg;ftdi;xugtdisub
		dnkjvncxklbncxmbnfudsgriyuzgbvjkdxvkbrdludlznfgymigncui</P>",
        "power": 0
    }
}
```

 **返回参数说明** 

| 参数名       | 类型   | 说明         |
| :----------- | :----- | ------------ |
| fid          | Long   | 编号         |
| name         | String | 基金理财简称 |
| interestRate | Long   | 利率         |
| time         | Date   | 开放交易日期 |
| baseline     | Long   | 最低起购金额 |
| yearRate     | Long   | 年利率       |
| investTime   | String | 投资时限     |
| unitPrice    | double | 每股单价     |
| number       | Long   | 全部数量     |
| positions    | Long   | 已购买数量   |
| introduction | String | 产品详细信息 |
| power        | int    | 产品分类     |



------



**简要描述：** 

- 购买产品接口

**请求URL：** 
- ` http://localhost:8080/api/v1/funds/pay?fid=123&money=10000 `

**请求方式：**
- POST 

**参数：** 

| 参数名 | 必选 | 类型   | 说明     |
| :----- | :--- | :----- | -------- |
| fid    | 是   | Long   | 产品编号 |
| money  | 是   | double | 购买金额 |

 **返回示例**

``` 
  {
    "status": 200,
    "message": "ok",
    "data": null
}
```

 **返回参数说明** 

| 参数名 | 类型   | 说明             |
| :----- | :----- | ---------------- |
| data   | String | null -->购买成功 |