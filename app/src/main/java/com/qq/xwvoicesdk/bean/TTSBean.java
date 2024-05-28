package com.qq.xwvoicesdk.bean;

import java.util.List;

/**
 * Created by 阿飞の小蝴蝶 on 2023/2/16
 * Describe:
 */
public class TTSBean {

    /**
     * query_id : MDBJBPFJOPGINGBJEGJBMFGMPEBLHBAA
     * skill_info : {"name":"无法识别","skill_id":"8dab4796-fa37-4114-ffff-ffffffffffff"}
     * skill_answer : {"answer_type":"audio","audio_info":{"text_answer":{"short_answer":"请讲"},"audio_resource_group":[{"content":"https://cloudim.weixin.qq.com/cloudim/cloudxw-bin/xwttsplay?tts_id=TTS08uEetaE0TQPHa4_1-7-N94GiPYixFXlmwGxnxGkt5KNnNJBhBE_5-6RqJV0Vqb0Wmd7l4W8tcSsLH28DhFueJ44FrEHnVd0SbV_f3WjrJAQProjZF6HZTFk3e892TBnPIkns8G2Mz_tGj3fJ8blOh0FrJRoKN50cfo1riVuAVY4e8W2ysCjt9kPIx5DPntjlW1bsjGgAcAj20i4ff_JZwdd0Z_ERF_flgXD0VR0Y_eZKIbgledl-GKmPYBjizF7lue0j-Mc9o4X_pbDoxezNhCS7ctgz-Pt6p7ZWiSBDy9WR--57RqWVBLGab4r2LixTQ-QUYrgH8Fjta7qo4aGXOqAAAAPo=","audio_type":"tts","mid":"MDBJBPFJOPGINGBJEGJBMFGMPEBLHBAA"}]}}
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
         * name : 无法识别
         * skill_id : 8dab4796-fa37-4114-ffff-ffffffffffff
         */

        private String name;
        private String skill_id;

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
    }

    public static class SkillAnswerBean {
        /**
         * answer_type : audio
         * audio_info : {"text_answer":{"short_answer":"请讲"},"audio_resource_group":[{"content":"https://cloudim.weixin.qq.com/cloudim/cloudxw-bin/xwttsplay?tts_id=TTS08uEetaE0TQPHa4_1-7-N94GiPYixFXlmwGxnxGkt5KNnNJBhBE_5-6RqJV0Vqb0Wmd7l4W8tcSsLH28DhFueJ44FrEHnVd0SbV_f3WjrJAQProjZF6HZTFk3e892TBnPIkns8G2Mz_tGj3fJ8blOh0FrJRoKN50cfo1riVuAVY4e8W2ysCjt9kPIx5DPntjlW1bsjGgAcAj20i4ff_JZwdd0Z_ERF_flgXD0VR0Y_eZKIbgledl-GKmPYBjizF7lue0j-Mc9o4X_pbDoxezNhCS7ctgz-Pt6p7ZWiSBDy9WR--57RqWVBLGab4r2LixTQ-QUYrgH8Fjta7qo4aGXOqAAAAPo=","audio_type":"tts","mid":"MDBJBPFJOPGINGBJEGJBMFGMPEBLHBAA"}]}
         */

        private String answer_type;
        private AudioInfoBean audio_info;

        public String getAnswer_type() {
            return answer_type;
        }

        public void setAnswer_type(String answer_type) {
            this.answer_type = answer_type;
        }

        public AudioInfoBean getAudio_info() {
            return audio_info;
        }

        public void setAudio_info(AudioInfoBean audio_info) {
            this.audio_info = audio_info;
        }

        public static class AudioInfoBean {
            /**
             * text_answer : {"short_answer":"请讲"}
             * audio_resource_group : [{"content":"https://cloudim.weixin.qq.com/cloudim/cloudxw-bin/xwttsplay?tts_id=TTS08uEetaE0TQPHa4_1-7-N94GiPYixFXlmwGxnxGkt5KNnNJBhBE_5-6RqJV0Vqb0Wmd7l4W8tcSsLH28DhFueJ44FrEHnVd0SbV_f3WjrJAQProjZF6HZTFk3e892TBnPIkns8G2Mz_tGj3fJ8blOh0FrJRoKN50cfo1riVuAVY4e8W2ysCjt9kPIx5DPntjlW1bsjGgAcAj20i4ff_JZwdd0Z_ERF_flgXD0VR0Y_eZKIbgledl-GKmPYBjizF7lue0j-Mc9o4X_pbDoxezNhCS7ctgz-Pt6p7ZWiSBDy9WR--57RqWVBLGab4r2LixTQ-QUYrgH8Fjta7qo4aGXOqAAAAPo=","audio_type":"tts","mid":"MDBJBPFJOPGINGBJEGJBMFGMPEBLHBAA"}]
             */

            private TextAnswerBean text_answer;
            private List<AudioResourceGroupBean> audio_resource_group;

            public TextAnswerBean getText_answer() {
                return text_answer;
            }

            public void setText_answer(TextAnswerBean text_answer) {
                this.text_answer = text_answer;
            }

            public List<AudioResourceGroupBean> getAudio_resource_group() {
                return audio_resource_group;
            }

            public void setAudio_resource_group(List<AudioResourceGroupBean> audio_resource_group) {
                this.audio_resource_group = audio_resource_group;
            }

            public static class TextAnswerBean {
                /**
                 * short_answer : 请讲
                 */

                private String short_answer;

                public String getShort_answer() {
                    return short_answer;
                }

                public void setShort_answer(String short_answer) {
                    this.short_answer = short_answer;
                }
            }

            public static class AudioResourceGroupBean {
                /**
                 * content : https://cloudim.weixin.qq.com/cloudim/cloudxw-bin/xwttsplay?tts_id=TTS08uEetaE0TQPHa4_1-7-N94GiPYixFXlmwGxnxGkt5KNnNJBhBE_5-6RqJV0Vqb0Wmd7l4W8tcSsLH28DhFueJ44FrEHnVd0SbV_f3WjrJAQProjZF6HZTFk3e892TBnPIkns8G2Mz_tGj3fJ8blOh0FrJRoKN50cfo1riVuAVY4e8W2ysCjt9kPIx5DPntjlW1bsjGgAcAj20i4ff_JZwdd0Z_ERF_flgXD0VR0Y_eZKIbgledl-GKmPYBjizF7lue0j-Mc9o4X_pbDoxezNhCS7ctgz-Pt6p7ZWiSBDy9WR--57RqWVBLGab4r2LixTQ-QUYrgH8Fjta7qo4aGXOqAAAAPo=
                 * audio_type : tts
                 * mid : MDBJBPFJOPGINGBJEGJBMFGMPEBLHBAA
                 */

                private String content;
                private String audio_type;
                private String mid;

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

                public String getMid() {
                    return mid;
                }

                public void setMid(String mid) {
                    this.mid = mid;
                }
            }
        }
    }
}
