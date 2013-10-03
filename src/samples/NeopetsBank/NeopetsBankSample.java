import com.neopets.auth.ClasspathPropertiesFileCredentialsProvider;
import com.neopets.auth.NeopetsCredentialsProvider;
import com.neopets.services.bank.NeopetsBank;
import com.neopets.services.bank.NeopetsBankClient;
import com.neopets.services.bank.model.BankRecord;
import com.neopets.services.bank.model.GetBankRecordResult;

public class NeopetsBankSample {

  public static final String CREDENTIALS_PATH = "/npCredentials.properties";
  public static final int DESIRED_NEOPOINTS_ON_HAND = 5000;

  public static void main(String[] args) throws Exception {
    NeopetsCredentialsProvider provider = new ClasspathPropertiesFileCredentialsProvider(CREDENTIALS_PATH);
    NeopetsBank bank = new NeopetsBankClient(provider.getCredentials());

    GetBankRecordResult results = bank.getBankRecord();
    BankRecord record = results.getBankRecord();

    displayBankRecordResults(results);

    if (record.canCollectInterest()) {
      System.out.println("[[  Collecting interest...  ]]");
      bank.collectInterest();

      results = bank.getBankRecord();
      record = results.getBankRecord();

      displayBankRecordResults(results);
    }

    int toAdjust = results.getPageStatus().getNeopointsOnHand() - DESIRED_NEOPOINTS_ON_HAND;

    if (toAdjust > 0) {
      System.out.println("[[  Depositing " + toAdjust + "...  ]]");
      bank.depositNeopoints(toAdjust);

      displayBankRecordResults(bank.getBankRecord());
    }
    else if (toAdjust < 0) {
      toAdjust *= -1;
      if (toAdjust <= record.getCurrentBalance()) {
        System.out.println("[[  Withdrawing " + toAdjust + "...  ]]");
        bank.withdrawNeopoints(toAdjust);

        displayBankRecordResults(bank.getBankRecord());
      }
    }
  }

  private static void displayBankRecordResults(GetBankRecordResult results) {
    BankRecord bankRecord = results.getBankRecord();

    System.out.println("===========================================");
    System.out.println("On Hand: " + results.getPageStatus().getNeopointsOnHand());
    System.out.println("Current Balance: " + bankRecord.getCurrentBalance());
    if (bankRecord.canCollectInterest()) {
      System.out.println("Can collect interest!");
    }
    else {
      System.out.println("Cannot collect interest.");
    }
    System.out.println("===========================================");
  }

}

