package com.neopets.services.bank;

import com.neopets.*;
import com.neopets.auth.NeopetsCredentials;
import com.neopets.services.bank.model.AlreadyClaimedInterestException;
import com.neopets.services.bank.model.GetBankRecordResult;
import com.neopets.services.bank.model.InvalidNeopointsAmountException;
import com.neopets.services.bank.model.WithdrawalLimitException;
import com.neopets.services.bank.model.handlers.AlreadyClaimedInterestExceptionHandler;
import com.neopets.services.bank.model.transform.GetBankRecordResultUnmarshaller;
import com.neopets.services.bank.model.handlers.InvalidNeopointsAmountExceptionHandler;
import com.neopets.services.bank.model.handlers.WithdrawalLimitExceptionHandler;
import com.neopets.transform.ErrorUnmarshaller;

import java.io.IOException;

public class NeopetsBankClient extends NeopetsClient implements NeopetsBank {

  public NeopetsBankClient(NeopetsCredentials credentials) throws IOException {
    super(credentials);
  }

  @Override
  public GetBankRecordResult getBankRecord() throws IOException {
    NeopetsRequest request = new NeopetsRequest(NeopetsURL.BANK);
    return invoke(request, new GetBankRecordResultUnmarshaller());
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

    invoke(request, new ErrorUnmarshaller())
            .handle(new InvalidNeopointsAmountExceptionHandler());
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

    invoke(request, new ErrorUnmarshaller())
            .handle(new InvalidNeopointsAmountExceptionHandler())
            .handle(new WithdrawalLimitExceptionHandler());
  }

  @Override
  public void collectInterest() throws IOException, AlreadyClaimedInterestException {
    NeopetsRequest request = new NeopetsRequest(NeopetsURL.PROCESS_BANK, "type=interest");
    request.setOrigin(NeopetsURL.HOME.toString());
    request.setToNotBeCached();

    invoke(request, new ErrorUnmarshaller())
            .handle(new AlreadyClaimedInterestExceptionHandler());
  }

}

