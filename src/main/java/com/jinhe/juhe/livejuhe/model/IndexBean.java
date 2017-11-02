package com.jinhe.juhe.livejuhe.model;

import java.util.List;

public class IndexBean {

    /**
     * msg : 成功
     * code : 200
     * data : [{"lives":68,"name":"星光","logo":"/img/2eda1d0abdd52f42495285e73d94d7f4.png","cid":1508441663},{"lives":29,"name":"东北虎","logo":"/img/d07abbff0c5c8588387e1dd1a472543d.png","cid":1506605707},{"lives":28,"name":"夜夜笙歌","logo":"/img/9101ba343b426054e6ae1eb6022e15c5.png","cid":1506683526},{"lives":52,"name":"泛果","logo":"/img/c33fb6d752e14a645f8ebbf112bd6341.png","cid":1508442238},{"lives":30,"name":"米兰","logo":"/img/aae2376a1cd296c26b9a31cec8cf839a.png","cid":1508246385},{"lives":21,"name":"大波浪","logo":"/img/09ea9933490cb977e5ec495197388da5.png","cid":1506683912},{"lives":57,"name":"逗趣","logo":"/img/976e051ba93e33c59bdfd255269e7c1d.png","cid":1506678740},{"lives":71,"name":"法拉秀","logo":"/img/d2e575ec43d263e1ac3f4d8cf8d4be26.png","cid":1507019268},{"lives":30,"name":"百媚","logo":"/img/80631633cc9aaa112c88e69febb3a452.png","cid":1507290958},{"lives":23,"name":"内衣秀","logo":"/img/1dc1e371f7f1bd48e9af2bbffe827abe.png","cid":1506974533},{"lives":28,"name":"爱美人","logo":"/img/752dd295d65974e8eabb59a29b5b1b4f.png","cid":1506540922},{"lives":6,"name":"流星雨","logo":"/img/bcef8a980b4aaa428b811f320f410592.png","cid":1506975249},{"lives":24,"name":"萌萌","logo":"/img/1513791764ab1d8118cc24a620fee41f.png","cid":1508153191},{"lives":30,"name":"飞鱼","logo":"/img/e2e7dfe2a4cd3136831ebaaee210e632.png","cid":1506605747},{"lives":27,"name":"一直播","logo":"/img/a038c080df5223f2a7c37e4ffb6af665.png","cid":1506616507},{"lives":29,"name":"爱吧","logo":"/img/e5867518d551540422f51dbae6eb2e17.png","cid":1506530902},{"lives":7,"name":"机器猫","logo":"/img/6005232e2421bc8d1779de6f36bcb905.png","cid":1506611051},{"lives":6,"name":"火星","logo":"/img/beb10096e0a541fbba4631d9b9027815.png","cid":1508857289},{"lives":25,"name":"小鱼直播","logo":"/img/04e805357922ae688a98a565768d1940.png","cid":1506974666},{"lives":17,"name":"北极星","logo":"/img/070cc6d870e683e8ae14dd380b9a2441.png","cid":1506700401},{"lives":34,"name":"陌秀","logo":"/img/0f9a95c6442b06c3087858f18bc5749f.png","cid":1506619832},{"lives":24,"name":"火凤凰","logo":"/img/c9a788811174f10c440e25abc1b3593c.png","cid":1506684050},{"lives":0,"name":"凤凰直播","logo":"/img/fa220b37721fe270ba658e7322aa400b.png","cid":1507287439},{"lives":20,"name":"本色","logo":"/img/8ffadba4ed30c412322d79a51e8a756c.png","cid":1506684150},{"lives":15,"name":"聊客","logo":"/img/0e929f11ec967f1a674e0b47f9cf95d8.png","cid":1506618820},{"lives":29,"name":"月舞","logo":"/img/ca412626dc113dc66d0869abe1631b53.png","cid":1506679669},{"lives":4,"name":"待秀","logo":"/img/45a8d24789560247264859e007774aed.png","cid":709260116},{"lives":4,"name":"豺狼","logo":"/img/076d73833820c001c805bcd4d7e8ceac.png","cid":1506683732},{"lives":15,"name":"九秀","logo":"/img/17f57e87546ba27f3c7403607ebdf15d.png","cid":1506619298},{"lives":21,"name":"后宫","logo":"/img/d19060e66076e3ef37df3c4df0144915.png","cid":1507302417},{"lives":5,"name":"情奔放","logo":"/img/7dde2a15647a7837afa306bfdaf009a8.png","cid":709260125},{"lives":18,"name":"蜻蜓","logo":"/img/6674b5aed2dabdbccf23f7c1417110b6.png","cid":709260124},{"lives":0,"name":"飞兔","logo":"/img/1cabae9ef48af58c0e2cb5141e66dd46.png","cid":1506676447},{"lives":30,"name":"海星","logo":"/img/b403c97fde65f8cccec2bc50ed500273.png","cid":1507555021},{"lives":6,"name":"金沙秀","logo":"/img/07bb1170fd40267a726b604538d6fb3c.png","cid":1507556327},{"lives":57,"name":"热猫","logo":"/img/f3f6b017d3fd52d1650a2e929ac654c9.png","cid":1507621005},{"lives":31,"name":"么么直播","logo":"/img/f507ec37d828c91af8050dc0587dece1.png","cid":1507899060},{"lives":4,"name":"天娇","logo":"/img/ec43153a395b1f69787e6d122ef2737e.png","cid":1507827102},{"lives":12,"name":"可乐","logo":"/img/678c6e5dca1f586bfb5862e29cc13cc3.png","cid":1507299420}]
     */
    public String msg;
    public int code;
    public List<DataEntity> data;

    public class DataEntity {
        /**
         * lives : 68
         * name : 星光
         * logo : /img/2eda1d0abdd52f42495285e73d94d7f4.png
         * cid : 1508441663
         */
        public int lives;
        public String name;
        public String logo;
        public int cid;
        public Platforminfo roomlist;
    }
}
