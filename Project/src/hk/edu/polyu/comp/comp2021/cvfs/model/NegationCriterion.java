//package hk.edu.polyu.comp.comp2021.cvfs.model;
//
//public class NegationCriterion extends Criterion{
//    private static final NegationCriterion isNotDocument = new NegationCriterion();
//    private boolean isDocumentCriterion;
//
//    private Criterion operand1;
//    private Criterion operand2;
//    String logicalOperator;
//    boolean negated = false;
//
//    public NegationCriterion(Criterion criterion) {
//        super(criterion.getCriName(), criterion.getAttrName(), criterion.getOp());
//        super.setVal(criterion.getVal());
//        negated = !negated;
//    }
//    public NegationCriterion(BinaryCriterion criterion) {
//        super(criterion.getCriName());
//        operand1 = criterion.operand1;
//        operand2 = criterion.operand2;
//        logicalOperator = criterion.logicalOperator;
//        negated = !negated;
//    }
//
//    public NegationCriterion() {
//        super(false);
//        isDocumentCriterion = false;
//    }
//    @Override
//    public boolean isPass(Entry entry) {
//
//        return !super.isPass(entry);
//    }
//
//    public NegationCriterion getIsNotDocument() {
//        return isNotDocument;
//    }
//
//    public String getLogicalOperator() {
//        return logicalOperator;
//    }
//    @Override
//    public String toString() {
//        if (isDocumentCriterion) {
//            return "Criterion isNotDocument";
//        }
//        String not = (negated) ? "Negative" : "";
//        return not + " " + operand1.toString() + " " + getLogicalOperator() + " " + operand2.toString();
//    }
//}
