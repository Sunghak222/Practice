package hk.edu.polyu.comp.comp2021.cvfs.model;

public class Criterion {
    private String criName;
    private String attrName;
    private String op;
    private Object val;
    private static final Criterion isDocument = new Criterion(true);
    private boolean isDocumentCriterion;
    private boolean isNegated = false;
    /*
    * val is Object because this field can be either string or int.
    *   val should be inputted with double quote.
    */

    /*
    * constructors
    */
    Criterion(String criName, String attrName, String op, String val, boolean isNegated) {
        this(criName,attrName,op);
        this.val = val;
        this.isNegated = isNegated;
    }

    Criterion(String criName, String attrName, String op, int val, boolean isNegated) {
        this(criName,attrName,op);
        this.val = val;
        this.isNegated = isNegated;
    }
    Criterion(String criName, String attrName, String op) {
        this.criName = criName;
        this.attrName = attrName;
        this.op = op;
        isDocumentCriterion = false;
    }
    /*
    * Constructor for only criName. Used for BinaryCriterion.
    */
    Criterion(String criName) {
        this.criName = criName;
        isDocumentCriterion = false;
    }

    /*
     * Constructor for isDocument.
     */
    public Criterion(boolean isDocumentBool) {
        if (isDocumentBool) {
            criName = "isDocument";
            isNegated = false;
        }
        else {
            criName = "isNotDocument";
            isNegated = true;
        }
        isDocumentCriterion = true;
    }


    /*
    * Check if the entry passes the criterion.
    * Assume that this criterion already passed isValidCriterion().
    */
    public boolean isPass(Entry entry) {
        if (isNegated) return !isPassNoNeg(entry);
        else return isPassNoNeg(entry);
    }

    public boolean isPassNoNeg(Entry entry) {
        if (entry == null) throw new IllegalArgumentException("Entry should not be null.");

        if (isDocumentCriterion) {
            return entry instanceof File;
        }

        /*val is in double quote.*/
        if (attrName.equals("Name")) {
            String tmp = (String) val;
            tmp = tmp.substring(1, tmp.length() - 1);
            if (entry.getName().contains(tmp)) return true;
            else return false;
        }
        else if (attrName.equals("Type")) {
            String tmp = (String) val;
            tmp = tmp.substring(1, tmp.length() - 1);
            if (!isDocument.isPass(entry))
                return false;
            else return ((File) entry).getType().equals(tmp);
        }
        else if (attrName.equals("Size")) {
            int sz = entry.getSize();
            int cmp = Integer.parseInt((String) val);;
            switch (op) {
                case ">" :
                    return (sz > cmp);
                case "<":
                    return (sz < cmp);
                case "<=":
                    return (sz <= cmp);
                case ">=":
                    return (sz >= cmp);
                case "=":
                    return (sz == cmp);
                case "!=":
                    return (sz != cmp);
            }
        }
        return true;
    }
    @Override
    public String toString() {
        //documentCriterion?
        if (isDocumentCriterion) {
            if (isNegated) return "Negation Criterion isDocument";
            else return "Criterion isDocument";
        }
        String neg = (isNegated) ? "Negation" : "";
        return neg + "Criterion " + getCriName() + " " + getAttrName() + " " + getOp() + " " + getVal();
    }
    /*getters of fields*/
    public String getCriName() {
        return criName;
    }

    public String getAttrName() {
        return attrName;
    }

    public String getOp() {
        return op;
    }

    public Object getVal() {
        return val;
    }
    public void setVal(Object val) {
        this.val = val;
    }
    public static Criterion getIsDocument() {
        return isDocument;
    }

    public boolean getBoolIsDocumentCriterion() {
        return isDocumentCriterion;
    }
    public boolean getIsNegated() {
        return isNegated;
    }
    public void setIsNegated(boolean isNegated) {
        this.isNegated = isNegated;
    }
}
