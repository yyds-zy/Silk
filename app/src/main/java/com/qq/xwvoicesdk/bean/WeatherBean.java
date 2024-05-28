package com.qq.xwvoicesdk.bean;

/**
 * Created by 阿飞の小蝴蝶 on 2023/2/16
 * Describe:
 */
public class WeatherBean {


    /**
     * query_id : FFHHJOGPIGLFAEDHIDJCDLFEBCHAOGFC
     * skill_info : {"name":"天气服务-新","skill_id":"10","skill_type":"system","desc":"未来十四天天气预报","icon":"https://xwwebimg-75050.gzc.vod.tencent-cloud.com/1648717169593.png","pub_time":1672235841,"version":"1.0"}
     * skill_answer : {"complete":true,"answer_type":"complex","complex_info":{"view_type":"card","card_info":{"title":"2月16日 北京市","content":"温度:-6°C-6°C  湿度:46%","icon":"https://xwwebimg-75050.gzc.vod.tencent-cloud.com/1654659709833.png","layout":"top","tts":{"content":"https://cloudim.weixin.qq.com/cloudim/cloudxw-bin/xwttsplay?tts_id=TTS08uEetaE0TQPHa4_1-7-N94Ddp6oG3qT574ObiM4FH1ymUc9b4aU7Kb0zcah0FNaDlPTBMrNUIUtisGOItTGOXD17-4j7MY27Ifjk9jm-vNqBlVJ79aUNzBwydk2OVuUCeKH5UYGG2LzAVq-9200FT8QHswna6HPV1b616kItedVuTBKvny1TQQsG3Lme-slcaR6nrq8aGTiKOenMXSOQnZBL99iqd3uzVEYqrUpK2PM-r3c6TUZ8NfvEL8goW0pSXS8ha77jN5vYwnA8LpDPHksVlnqUP3nljkQNgu-KC5YTrbMxKNj2EuJqkWVNbxGwCCTHb-8vlusQ5ECJ0pF3li4bn0jSePoqWWn99b04r36cwQIp_MjlrXaqDQWV7OF7nAAABGA==","audio_type":"tts"},"short_answer":"北京市今天晴，温度-6到6度，当前温度3度，空气质量良。天气干冷，穿厚一点吧！"}}}
     */

    private String query_id;
    private SkillInfoBean skill_info;
    private SkillAnswerBean skill_answer;

    public String getQuery_id() {
        return query_id;
    }

    public void setQuery_id(String query_id) {
        this.query_id = query_id;
    }

    public SkillInfoBean getSkill_info() {
        return skill_info;
    }

    public void setSkill_info(SkillInfoBean skill_info) {
        this.skill_info = skill_info;
    }

    public SkillAnswerBean getSkill_answer() {
        return skill_answer;
    }

    public void setSkill_answer(SkillAnswerBean skill_answer) {
        this.skill_answer = skill_answer;
    }

    public static class SkillInfoBean {
        /**
         * name : 天气服务-新
         * skill_id : 10
         * skill_type : system
         * desc : 未来十四天天气预报
         * icon : https://xwwebimg-75050.gzc.vod.tencent-cloud.com/1648717169593.png
         * pub_time : 1672235841
         * version : 1.0
         */

        private String name;
        private String skill_id;
        private String skill_type;
        private String desc;
        private String icon;
        private int pub_time;
        private String version;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSkill_id() {
            return skill_id;
        }

        public void setSkill_id(String skill_id) {
            this.skill_id = skill_id;
        }

        public String getSkill_type() {
            return skill_type;
        }

        public void setSkill_type(String skill_type) {
            this.skill_type = skill_type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getPub_time() {
            return pub_time;
        }

        public void setPub_time(int pub_time) {
            this.pub_time = pub_time;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    public static class SkillAnswerBean {
        /**
         * complete : true
         * answer_type : complex
         * complex_info : {"view_type":"card","card_info":{"title":"2月16日 北京市","content":"温度:-6°C-6°C  湿度:46%","icon":"https://xwwebimg-75050.gzc.vod.tencent-cloud.com/1654659709833.png","layout":"top","tts":{"content":"https://cloudim.weixin.qq.com/cloudim/cloudxw-bin/xwttsplay?tts_id=TTS08uEetaE0TQPHa4_1-7-N94Ddp6oG3qT574ObiM4FH1ymUc9b4aU7Kb0zcah0FNaDlPTBMrNUIUtisGOItTGOXD17-4j7MY27Ifjk9jm-vNqBlVJ79aUNzBwydk2OVuUCeKH5UYGG2LzAVq-9200FT8QHswna6HPV1b616kItedVuTBKvny1TQQsG3Lme-slcaR6nrq8aGTiKOenMXSOQnZBL99iqd3uzVEYqrUpK2PM-r3c6TUZ8NfvEL8goW0pSXS8ha77jN5vYwnA8LpDPHksVlnqUP3nljkQNgu-KC5YTrbMxKNj2EuJqkWVNbxGwCCTHb-8vlusQ5ECJ0pF3li4bn0jSePoqWWn99b04r36cwQIp_MjlrXaqDQWV7OF7nAAABGA==","audio_type":"tts"},"short_answer":"北京市今天晴，温度-6到6度，当前温度3度，空气质量良。天气干冷，穿厚一点吧！"}}
         */

        private boolean complete;
        private String answer_type;
        private ComplexInfoBean complex_info;

        public boolean isComplete() {
            return complete;
        }

        public void setComplete(boolean complete) {
            this.complete = complete;
        }

        public String getAnswer_type() {
            return answer_type;
        }

        public void setAnswer_type(String answer_type) {
            this.answer_type = answer_type;
        }

        public ComplexInfoBean getComplex_info() {
            return complex_info;
        }

        public void setComplex_info(ComplexInfoBean complex_info) {
            this.complex_info = complex_info;
        }

        public static class ComplexInfoBean {
            /**
             * view_type : card
             * card_info : {"title":"2月16日 北京市","content":"温度:-6°C-6°C  湿度:46%","icon":"https://xwwebimg-75050.gzc.vod.tencent-cloud.com/1654659709833.png","layout":"top","tts":{"content":"https://cloudim.weixin.qq.com/cloudim/cloudxw-bin/xwttsplay?tts_id=TTS08uEetaE0TQPHa4_1-7-N94Ddp6oG3qT574ObiM4FH1ymUc9b4aU7Kb0zcah0FNaDlPTBMrNUIUtisGOItTGOXD17-4j7MY27Ifjk9jm-vNqBlVJ79aUNzBwydk2OVuUCeKH5UYGG2LzAVq-9200FT8QHswna6HPV1b616kItedVuTBKvny1TQQsG3Lme-slcaR6nrq8aGTiKOenMXSOQnZBL99iqd3uzVEYqrUpK2PM-r3c6TUZ8NfvEL8goW0pSXS8ha77jN5vYwnA8LpDPHksVlnqUP3nljkQNgu-KC5YTrbMxKNj2EuJqkWVNbxGwCCTHb-8vlusQ5ECJ0pF3li4bn0jSePoqWWn99b04r36cwQIp_MjlrXaqDQWV7OF7nAAABGA==","audio_type":"tts"},"short_answer":"北京市今天晴，温度-6到6度，当前温度3度，空气质量良。天气干冷，穿厚一点吧！"}
             */

            private String view_type;
            private CardInfoBean card_info;

            public String getView_type() {
                return view_type;
            }

            public void setView_type(String view_type) {
                this.view_type = view_type;
            }

            public CardInfoBean getCard_info() {
                return card_info;
            }

            public void setCard_info(CardInfoBean card_info) {
                this.card_info = card_info;
            }

            public static class CardInfoBean {
                /**
                 * title : 2月16日 北京市
                 * content : 温度:-6°C-6°C  湿度:46%
                 * icon : https://xwwebimg-75050.gzc.vod.tencent-cloud.com/1654659709833.png
                 * layout : top
                 * tts : {"content":"https://cloudim.weixin.qq.com/cloudim/cloudxw-bin/xwttsplay?tts_id=TTS08uEetaE0TQPHa4_1-7-N94Ddp6oG3qT574ObiM4FH1ymUc9b4aU7Kb0zcah0FNaDlPTBMrNUIUtisGOItTGOXD17-4j7MY27Ifjk9jm-vNqBlVJ79aUNzBwydk2OVuUCeKH5UYGG2LzAVq-9200FT8QHswna6HPV1b616kItedVuTBKvny1TQQsG3Lme-slcaR6nrq8aGTiKOenMXSOQnZBL99iqd3uzVEYqrUpK2PM-r3c6TUZ8NfvEL8goW0pSXS8ha77jN5vYwnA8LpDPHksVlnqUP3nljkQNgu-KC5YTrbMxKNj2EuJqkWVNbxGwCCTHb-8vlusQ5ECJ0pF3li4bn0jSePoqWWn99b04r36cwQIp_MjlrXaqDQWV7OF7nAAABGA==","audio_type":"tts"}
                 * short_answer : 北京市今天晴，温度-6到6度，当前温度3度，空气质量良。天气干冷，穿厚一点吧！
                 */

                private String title;
                private String content;
                private String icon;
                private String layout;
                private TtsBean tts;
                private String short_answer;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getLayout() {
                    return layout;
                }

                public void setLayout(String layout) {
                    this.layout = layout;
                }

                public TtsBean getTts() {
                    return tts;
                }

                public void setTts(TtsBean tts) {
                    this.tts = tts;
                }

                public String getShort_answer() {
                    return short_answer;
                }

                public void setShort_answer(String short_answer) {
                    this.short_answer = short_answer;
                }

                public static class TtsBean {
                    /**
                     * content : https://cloudim.weixin.qq.com/cloudim/cloudxw-bin/xwttsplay?tts_id=TTS08uEetaE0TQPHa4_1-7-N94Ddp6oG3qT574ObiM4FH1ymUc9b4aU7Kb0zcah0FNaDlPTBMrNUIUtisGOItTGOXD17-4j7MY27Ifjk9jm-vNqBlVJ79aUNzBwydk2OVuUCeKH5UYGG2LzAVq-9200FT8QHswna6HPV1b616kItedVuTBKvny1TQQsG3Lme-slcaR6nrq8aGTiKOenMXSOQnZBL99iqd3uzVEYqrUpK2PM-r3c6TUZ8NfvEL8goW0pSXS8ha77jN5vYwnA8LpDPHksVlnqUP3nljkQNgu-KC5YTrbMxKNj2EuJqkWVNbxGwCCTHb-8vlusQ5ECJ0pF3li4bn0jSePoqWWn99b04r36cwQIp_MjlrXaqDQWV7OF7nAAABGA==
                     * audio_type : tts
                     */

                    private String content;
                    private String audio_type;

                    public String getContent() {
                        return content;
                    }

                    public void setContent(String content) {
                        this.content = content;
                    }

                    public String getAudio_type() {
                        return audio_type;
                    }

                    public void setAudio_type(String audio_type) {
                        this.audio_type = audio_type;
                    }
                }
            }
        }
    }
}
