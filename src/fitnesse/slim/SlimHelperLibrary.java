package fitnesse.slim;

import java.util.List;
import java.util.Stack;

public class SlimHelperLibrary implements StatementExecutorConsumer {
  private static final String ACTOR_INSTANCE_NAME = "scriptTableActor";
  private StatementExecutorInterface statementExecutor;
  private Stack<Object> fixtureStack = new Stack<Object>();

  public Object getFixture() {
    return statementExecutor.getInstance(ACTOR_INSTANCE_NAME);
  }

  public void setStatementExecutor(StatementExecutorInterface statementExecutor) {
    this.statementExecutor = statementExecutor;
  }

  public StatementExecutorInterface getStatementExecutor() {
    return statementExecutor;
  }

  public void pushFixture() {
    fixtureStack.push(getFixture());
  }

  public void popFixture() {
    Object actor = fixtureStack.pop();
    statementExecutor.setInstance(ACTOR_INSTANCE_NAME, actor);
  }
  
  // The following functions are used to manipulate Symbols from the Slim Tables
  public Object cloneSymbol(Object master){
  return master;
  }
  public Object freeSymbol(){
  return null;
  }
  public Object getValuefromQueryResultSymbol(List<List<List<Object>>> queryResult, int rowNo, String columnName ){
  List<List<Object>> row = queryResult.get(rowNo);
  for(int i=0; i< row.size(); i++){
  if(columnName.compareTo( (String) (row.get(i).get(0))) == 0){
  return row.get(i).get(1);
  }
  }
  throw new RuntimeException("No column with name '" + columnName + "' found in row " + rowNo);
  }
  public Object getValuefromTableResultSymbol(List<List<Object>> tableResult, int rowNo, int columnNo ){
  return tableResult.get(rowNo).get(columnNo);
  }
}
