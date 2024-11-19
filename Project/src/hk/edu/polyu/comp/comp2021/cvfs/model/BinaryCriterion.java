package hk.edu.polyu.comp.comp2021.cvfs.model;

public class BinaryCriterion extends Criterion{
    Criterion operand1;
    Criterion operand2;
    String logicalOperator;

    BinaryCriterion(String criName, Criterion operand1, Criterion operand2, String logicalOperator, boolean isNegated) {
        super(criName);
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.logicalOperator = logicalOperator;
        super.setIsNegated(isNegated);
    }

    @Override
    public boolean isPass(Entry entry) {
        if (logicalOperator.equals("&&")) return operand1.isPass(entry) && operand2.isPass(entry);
        else if (logicalOperator.equals("||")) return operand1.isPass(entry) || operand2.isPass(entry);
        else return false;
    }

    @Override
    public String toString() {
        return "BinaryCriterion " + getCriName() + " " + operand1 + " " + getOp() + " " + operand2;
    }
    @Override
    public String getOp() {
        return logicalOperator;
    }
    public Criterion getOperand1() {
        return operand1;
    }
    public Criterion getOperand2() {
        return operand2;
    }
}
