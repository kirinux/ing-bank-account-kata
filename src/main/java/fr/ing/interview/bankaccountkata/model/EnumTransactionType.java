package fr.ing.interview.bankaccountkata.model;

public enum EnumTransactionType {


        DEPOSIT("deposit"), WITH_DRAW("withdraw");

        private String value;

        private EnumTransactionType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }


}
