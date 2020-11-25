public enum Option {
  RESET('r', "Reset game", null),
  LEFT('a', "Move left", Direction.LEFT),
  RIGHT('d', "Move right", Direction.RIGHT),
  UP('w', "Move up", Direction.UP),
  DOWN('s', "Move down", Direction.DOWN),
  EXIT('q', "Exit the game", null);

  private char userInput;
  private String description;
  private Direction direction;

  private Option(char userInput, String description, Direction direction) {
    this.userInput = userInput;
    this.description = description;
    this.direction = direction;
  }

  public char getUserInput() {
    return this.userInput;
  }

  public String getDescription() {
    return this.description;
  }

  public Direction getDirection() {
    return this.direction;
  }

  public String toString() {
    StringBuilder strBuilder = new StringBuilder("'" + this.userInput + "' ==> opcja " + this.name() + ", opis: " + this.description);
    if (this.userInput != 'q' && this.userInput != 'r') {
      strBuilder.append(", wektor przesuniecia: " + this.direction);
    }

    return strBuilder.toString();
  }

}
