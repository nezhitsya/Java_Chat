package prac;

public class zipDAO {
        private String seq;
        private String zipcode;
        private String sido;
        private String gugun;
        private String dong;
        private String ri;
        private String st_bunji;
        private String ed_bunji;
      //각각의 주소 단위를 String형태로 지정해주어 문자열로 받아올수 있도록 하였다.
      //또한 get()과 set()을 이용하여 데이터 값을 반환하거나 셋팅시켜주는 메소드를 추가시켜주었다.
        
        public String getSeq() {
               return seq;
        }

        public void setSeq(String seq) {
               this.seq = seq;
        }

        public String getZipcode() {
               return zipcode;
        }

        public void setZipcode(String zipcode) {
               this.zipcode = zipcode;
        }

        public String getSido() {
               return sido;
        }

        public void setSido(String sido) {
               this.sido = sido;
        }

        public String getGugun() {
               return gugun;
        }

        public void setGugun(String gugun) {
               this.gugun = gugun;
        }

        public String getDong() {
               return dong;
        }

        public void setDong(String dong) {
               this.dong = dong;
        }

        public String getRi() {
               return ri;
        }
        
        public void setRi(String ri) {
               this.ri = ri;
        }

        public String getSt_bunji() {
               return st_bunji;
        }

        public void setSt_bunji(String st_bunji) {
               this.st_bunji = st_bunji;
        }
        
        public String getEd_bunji() {
               return ed_bunji;
        }

        public void setEd_bunji(String ed_bunji) {
               this.ed_bunji = ed_bunji;
        }
}
