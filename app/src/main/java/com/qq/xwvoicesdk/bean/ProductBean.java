package com.qq.xwvoicesdk.bean;

import java.util.List;

/**
 * Created by 阿飞の小蝴蝶 on 2023/2/17
 * Describe:
 */
public class ProductBean {

    /**
     * data : {"product_info":{"app_uin":3873,"device_name":"当贝电视盒","device_logo":"http://xwwebimg-75050.gzc.vod.tencent-cloud.com/1676445352342.png","device_model":"ShellM1","device_desc":"当贝电视盒M1"},"device_info":{"device_uin":45612982189666752,"qr_ticket":"http://we.qq.com/d/AQC5nlPWpKbIVuoYNUXYmJicN-Gn8VMkMR2HAruv","app_uin":3873,"sn":"shell001","device_id":"gh_1a4ecf97bca3_8241c935ef60b810","binder":[{"binder_id":"ouZ5T1oOM46UZNNlUf1c6l0ebJpc","xw_uin":25985326202878176}]}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * product_info : {"app_uin":3873,"device_name":"当贝电视盒","device_logo":"http://xwwebimg-75050.gzc.vod.tencent-cloud.com/1676445352342.png","device_model":"ShellM1","device_desc":"当贝电视盒M1"}
         * device_info : {"device_uin":45612982189666752,"qr_ticket":"http://we.qq.com/d/AQC5nlPWpKbIVuoYNUXYmJicN-Gn8VMkMR2HAruv","app_uin":3873,"sn":"shell001","device_id":"gh_1a4ecf97bca3_8241c935ef60b810","binder":[{"binder_id":"ouZ5T1oOM46UZNNlUf1c6l0ebJpc","xw_uin":25985326202878176}]}
         */

        private ProductInfoBean product_info;
        private DeviceInfoBean device_info;

        public ProductInfoBean getProduct_info() {
            return product_info;
        }

        public void setProduct_info(ProductInfoBean product_info) {
            this.product_info = product_info;
        }

        public DeviceInfoBean getDevice_info() {
            return device_info;
        }

        public void setDevice_info(DeviceInfoBean device_info) {
            this.device_info = device_info;
        }

        public static class ProductInfoBean {
            /**
             * app_uin : 3873
             * device_name : 当贝电视盒
             * device_logo : http://xwwebimg-75050.gzc.vod.tencent-cloud.com/1676445352342.png
             * device_model : ShellM1
             * device_desc : 当贝电视盒M1
             */

            private int app_uin;
            private String device_name;
            private String device_logo;
            private String device_model;
            private String device_desc;

            public int getApp_uin() {
                return app_uin;
            }

            public void setApp_uin(int app_uin) {
                this.app_uin = app_uin;
            }

            public String getDevice_name() {
                return device_name;
            }

            public void setDevice_name(String device_name) {
                this.device_name = device_name;
            }

            public String getDevice_logo() {
                return device_logo;
            }

            public void setDevice_logo(String device_logo) {
                this.device_logo = device_logo;
            }

            public String getDevice_model() {
                return device_model;
            }

            public void setDevice_model(String device_model) {
                this.device_model = device_model;
            }

            public String getDevice_desc() {
                return device_desc;
            }

            public void setDevice_desc(String device_desc) {
                this.device_desc = device_desc;
            }
        }

        public static class DeviceInfoBean {
            /**
             * device_uin : 45612982189666752
             * qr_ticket : http://we.qq.com/d/AQC5nlPWpKbIVuoYNUXYmJicN-Gn8VMkMR2HAruv
             * app_uin : 3873
             * sn : shell001
             * device_id : gh_1a4ecf97bca3_8241c935ef60b810
             * binder : [{"binder_id":"ouZ5T1oOM46UZNNlUf1c6l0ebJpc","xw_uin":25985326202878176}]
             */

            private long device_uin;
            private String qr_ticket;
            private int app_uin;
            private String sn;
            private String device_id;
            private List<BinderBean> binder;

            public long getDevice_uin() {
                return device_uin;
            }

            public void setDevice_uin(long device_uin) {
                this.device_uin = device_uin;
            }

            public String getQr_ticket() {
                return qr_ticket;
            }

            public void setQr_ticket(String qr_ticket) {
                this.qr_ticket = qr_ticket;
            }

            public int getApp_uin() {
                return app_uin;
            }

            public void setApp_uin(int app_uin) {
                this.app_uin = app_uin;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getDevice_id() {
                return device_id;
            }

            public void setDevice_id(String device_id) {
                this.device_id = device_id;
            }

            public List<BinderBean> getBinder() {
                return binder;
            }

            public void setBinder(List<BinderBean> binder) {
                this.binder = binder;
            }

            public static class BinderBean {
                /**
                 * binder_id : ouZ5T1oOM46UZNNlUf1c6l0ebJpc
                 * xw_uin : 25985326202878176
                 */

                private String binder_id;
                private long xw_uin;

                public String getBinder_id() {
                    return binder_id;
                }

                public void setBinder_id(String binder_id) {
                    this.binder_id = binder_id;
                }

                public long getXw_uin() {
                    return xw_uin;
                }

                public void setXw_uin(long xw_uin) {
                    this.xw_uin = xw_uin;
                }
            }
        }
    }
}
