/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : book_sys

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 17/10/2023 07:38:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `descr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '简介',
  `price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '价格',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '作者',
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '出版日期',
  `publisher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '出版社',
  `store` int(11) NULL DEFAULT NULL COMMENT '数量',
  `active` tinyint(1) NULL DEFAULT 1 COMMENT '上架',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '漫长的旅途（当当专享亲签版+飞机盒，卢思浩继《你也走了很远的路吧》之后，暌违六年，短篇小说集力作）', '这本书献给每一个遇到过的人，写给每一个在向前走的人。随书附赠卢思浩致读者信+定制信封（信封限量，售完为止）+海报+明信片+“漫长旅途”守护卡+金句卡。', '33', 'http://localhost:9091/file/29607173-1_u_1691459538_1648jpg', '卢思浩', '2023-08-01', '湖南文艺出版社', 999, 1);
INSERT INTO `book` VALUES (2, '小学生社交情商漫画（提升社交力，培养高情商，甄选真朋友，拒绝被霸凌。全2册，歪歪兔童书馆出品）', '60条提升孩子社交力、处理同学关系、伙伴关系、师生关系的方法，用贴近生活的漫画故事展现，让孩子学会有效沟通，结交优秀的朋友，避免校园霸凌。提升自我认同感，增加共情力、抗挫力。', '38.80', 'http://localhost:9091/file/29602308-1_w_1692335442_3033jpg', '歪歪兔童书馆', '2023-08-01', '海豚出版社', 9996, 1);
INSERT INTO `book` VALUES (3, '艺术终结者：签名版（一本书解构名画背后的秘密，语言风趣幽默，笑料不断！）', '你从未见过的艺术史犯罪！ 令人“哭笑不得”的艺术品犯罪，书中展示犯罪现场图像，还原现场“杯盘狼藉”。', '49.30', 'http://localhost:9091/file/29608146-1_w_1691980944_8892jpg', '吕宸', '2023-08-01', '中译出版社（原中国对外翻译出版公司）', 9999, 0);
INSERT INTO `book` VALUES (4, '自律的人生更自由', '彻底摆脱杂、乱、忙，做自己人生的掌控者', '12.90', 'http://localhost:9091/file/4ac953f638e04a34_9440jpg', '小椰子111', '2023-08-01', '古吴轩出版社', 9999, 1);

-- ----------------------------
-- Table structure for book_borrow
-- ----------------------------
DROP TABLE IF EXISTS `book_borrow`;
CREATE TABLE `book_borrow`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NULL DEFAULT NULL COMMENT '图书id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '借书人id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '借书人',
  `num` int(11) NULL DEFAULT NULL COMMENT '借书数量',
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '借书日期',
  `back` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '否' COMMENT '是否归还',
  `backdate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '还书日期',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '待审核' COMMENT '审核状态',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审核意见',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of book_borrow
-- ----------------------------
INSERT INTO `book_borrow` VALUES (16, 4, 5, '小可爱718851号', 1, '2023-10-16', '否', '2023-10-17', '通过', NULL);
INSERT INTO `book_borrow` VALUES (17, 2, 5, '小可爱718851号', 2, '2023-10-16', '否', '2023-10-18', '拒绝', '暂时不外借');
INSERT INTO `book_borrow` VALUES (18, 1, 5, '小可爱718851号', 1, '2023-10-16', '否', '2023-10-19', '通过', NULL);
INSERT INTO `book_borrow` VALUES (19, 4, 3, '阿卡丽', 1, '2023-10-16', '是', '2023-10-19', '通过', '通过');
INSERT INTO `book_borrow` VALUES (20, 4, 3, '阿卡丽', 1, '2023-10-16', '是', '2023-10-18', '通过', '可以');
INSERT INTO `book_borrow` VALUES (21, 4, 6, '小可爱773328号', 2, '2023-10-16', '是', '2023-10-17', '通过', '允许外借');
INSERT INTO `book_borrow` VALUES (22, 2, 6, '小可爱773328号', 1, '2023-10-16', '否', '2023-10-19', '拒绝', '不允许外借');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `price` int(255) NULL DEFAULT NULL COMMENT '积分',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面',
  `store` int(11) NULL DEFAULT NULL COMMENT '数量',
  `active` tinyint(1) NULL DEFAULT 1 COMMENT '上架',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '漫长的旅途（当当专享亲签版+飞机盒，卢思浩继《你也走了很远的路吧》之后，暌违六年，短篇小说集力作）', 33, 'http://localhost:9091/file/29607173-1_u_1691459538_1648jpg', 1000, 1);
INSERT INTO `goods` VALUES (2, '小学生社交情商漫画（提升社交力，培养高情商，甄选真朋友，拒绝被霸凌。全2册，歪歪兔童书馆出品）', 39, 'http://localhost:9091/file/29602308-1_w_1692335442_3033jpg', 10000, 1);
INSERT INTO `goods` VALUES (3, '艺术终结者：签名版（一本书解构名画背后的秘密，语言风趣幽默，笑料不断！）', 49, 'http://localhost:9091/file/29608146-1_w_1691980944_8892jpg', 9997, 0);
INSERT INTO `goods` VALUES (4, '自律的人生更自由', 12, 'http://localhost:9091/file/4ac953f638e04a34_9440jpg', 9990, 1);

-- ----------------------------
-- Table structure for goods_exchange
-- ----------------------------
DROP TABLE IF EXISTS `goods_exchange`;
CREATE TABLE `goods_exchange`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '兑换人id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '兑换人',
  `num` int(11) NULL DEFAULT NULL COMMENT '兑换数量',
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '兑换日期',
  `account` int(11) NULL DEFAULT NULL COMMENT '扣除积分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods_exchange
-- ----------------------------
INSERT INTO `goods_exchange` VALUES (14, 4, 3, '阿卡丽', 1, '2023-10-16', 12);
INSERT INTO `goods_exchange` VALUES (15, 4, 3, '阿卡丽', 2, '2023-10-16', 24);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '文学类图书限时免费借阅', '文学类图书限时免费借阅', '2023-08-23');
INSERT INTO `notice` VALUES (2, '图书馆开馆时间早上9点', '图书馆开馆时间早上9点', '2023-08-23');
INSERT INTO `notice` VALUES (3, '图书借阅，多读书，读好书', '图书借阅，多读书，读好书', '2023-08-23');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `birth` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '生日',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `disable` tinyint(1) NULL DEFAULT 0 COMMENT '禁用',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'USER' COMMENT '角色',
  `account` int(11) NULL DEFAULT 0 COMMENT '积分',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', '管理员', '男', '2000-08-16', '13688559966', '安徽省合肥市', 'http://localhost:9091/file/%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20230801220015_9613png', 0, 'ADMIN', 0);
INSERT INTO `user` VALUES (2, 'gbb', '123', '小可爱587434号', '男', '1993-10-1', '13699885599', '安徽合肥', 'http://localhost:9090/file/%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20220706191248.png', 0, 'USER', 10);
INSERT INTO `user` VALUES (3, 'acc', '123', '阿卡丽', '女', '1998-10-08', '13655228899', '北京1', 'http://localhost:9091/file/%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20230801215955_5816png', 0, 'USER', 28);
INSERT INTO `user` VALUES (4, 'qcc', '123', '小可爱939998号', NULL, NULL, NULL, NULL, NULL, 0, 'USER', 7);
INSERT INTO `user` VALUES (5, 'bcc', '123', '小可爱718851号', NULL, NULL, NULL, NULL, 'http://localhost:9091/file/31232.png', 0, 'USER', 0);
INSERT INTO `user` VALUES (6, 'zhang', '123', '小可爱773328号', NULL, NULL, NULL, NULL, NULL, 1, 'USER', 10);

SET FOREIGN_KEY_CHECKS = 1;
