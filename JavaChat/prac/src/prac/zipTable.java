package prac;

import javax.swing.table.AbstractTableModel;

public class zipTable extends AbstractTableModel {
        //컬럼의 이름
        String[] columNames =
{"일련번호","우편번호","시.도","구.군","동","리","시작번지","마지막번지"};
        //데이터
        Object[][] data = {{" ", " "," "," "," "," "," "," "}};

        public zipTable(){
        }

        public zipTable(Object[][] data) {
               this.data = data;
        }

        @Override
        public int getColumnCount() {
               // TODO Auto-generated method stub
               return columNames.length;
        }

        @Override
        public int getRowCount() {
               // TODO Auto-generated method stub
               return data.length;           //2차 배열의 길이
        }
        
        @Override
        public Object getValueAt(int arg0, int arg1) {
               // TODO Auto-generated method stub
               return data[arg0][arg1];
        }
        
        @Override
        public String getColumnName(int arg0) {
               // TODO Auto-generated method stub
               return columNames[arg0];
        }
}