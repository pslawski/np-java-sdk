package com.neopets.services.bank;

import com.neopets.*;
import com.neopets.auth.NeopetsCredentials;
import com.neopets.services.bank.model.AlreadyClaimedInterestException;
import com.neopets.services.bank.model.BankRecordResults;
import com.neopets.services.bank.model.InvalidNeopointsAmountException;
import com.neopets.services.bank.model.WithdrawalLimitException;
import com.neopets.services.bank.model.transform.BankRecordUnmarshaller;
import org.jsoup.Jsoup;

import java.io.IOException;

public class NeopetsBankClient extends NeopetsClient implements NeopetsBank {

  public NeopetsBankClient(NeopetsCredentials credentials) throws IOException {
    super(credentials);
  }

  @Override
  public BankRecordResults getBankRecord() throws IOException {
    NeopetsRequest request = new NeopetsRequest(NeopetsURL.BANK);

    NeopetsResponse response = execute(request);

    BankRecordUnmarshaller unmarshaller = new BankRecordUnmarshaller();
    return unmarshaller.unmarshall(Jsoup.parse(response.getContents()));
  }

  @Override
  public void depositNeopoints(int neopoints) throws IOException, InvalidNeopointsAmountException {
    if (neopoints <= 0) {
      throw new IllegalArgumentException("Neopoints cannot be less than or equal to zero.");
    }

    String data = "type=deposit&amount=" + neopoints;
    NeopetsRequest request = new NeopetsRequest(NeopetsURL.PROCESS_BANK, data);
    request.setOrigin(NeopetsURL.HOME.toString());
    request.setToNotBeCached();

    NeopetsResponse response = execute(request);

    NeopetsErrorResults results = new NeopetsErrorResults(response.getContents());
    if (results.didErrorOccur()) {
      throw new InvalidNeopointsAmountException(results.getErrorMessage());
    }
  }

  @Override
  public void withdrawNeopoints(int neopoints) throws IOException, WithdrawalLimitException,
          InvalidNeopointsAmountException {
    if (neopoints <= 0) {
      throw new IllegalArgumentException("Neopoints cannot be less than or equal to zero.");
    }

    String data = "type=withdraw&amount=" + neopoints;
    NeopetsRequest request = new NeopetsRequest(NeopetsURL.PROCESS_BANK, data);
    request.setOrigin(NeopetsURL.HOME.toString());
    request.setToNotBeCached();

    NeopetsResponse response = execute(request);

    NeopetsErrorResults results = new NeopetsErrorResults(response.getContents());
    if (results.didErrorOccur()) {
      String message = results.getErrorMessage();

      if (message.contains("you have already attempted to withdraw")) {
        throw new WithdrawalLimitException(message);
      }
      else {
        throw new InvalidNeopointsAmountException(message);
      }
    }
  }

  @Override
  public void collectInterest() throws IOException, AlreadyClaimedInterestException {
    NeopetsRequest request = new NeopetsRequest(NeopetsURL.PROCESS_BANK, "type=interest");
    request.setOrigin(NeopetsURL.HOME.toString());
    request.setToNotBeCached();

    NeopetsResponse response = execute(request);

    NeopetsErrorResults results = new NeopetsErrorResults(response.getContents());
    if (results.didErrorOccur()) {
      throw new AlreadyClaimedInterestException(results.getErrorMessage());
    }
  }

}

