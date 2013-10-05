package com.neopets.services.bank;

import com.neopets.*;
import com.neopets.auth.NeopetsCredentials;
import com.neopets.services.bank.model.AlreadyClaimedInterestException;
import com.neopets.services.bank.model.CollectInterestRequest;
import com.neopets.services.bank.model.DepositNeopointsRequest;
import com.neopets.services.bank.model.GetBankRecordResult;
import com.neopets.services.bank.model.InvalidNeopointsAmountException;
import com.neopets.services.bank.model.WithdrawNeopointsRequest;
import com.neopets.services.bank.model.WithdrawalLimitException;
import com.neopets.services.bank.model.handlers.AlreadyClaimedInterestExceptionHandler;
import com.neopets.services.bank.model.transform.CollectInterestRequestMarshaller;
import com.neopets.services.bank.model.transform.DepositNeopointsRequestMarshaller;
import com.neopets.services.bank.model.transform.GetBankRecordResultUnmarshaller;
import com.neopets.services.bank.model.handlers.InvalidNeopointsAmountExceptionHandler;
import com.neopets.services.bank.model.handlers.WithdrawalLimitExceptionHandler;
import com.neopets.services.bank.model.transform.WithdrawNeopointsRequestMarshaller;
import com.neopets.transform.ErrorUnmarshaller;

/**
 * Client for accessing the Neopets Bank. All requests are blocking and will return when completed.
 */
public class NeopetsBankClient extends NeopetsClient implements NeopetsBank {

  /**
   * Construct a new client to invoke requests to the Neopets Bank using the given Neopets account credentials.
   *
   * @param credentials
   *         The account credentials to be used.
   */
  public NeopetsBankClient(NeopetsCredentials credentials) {
    super(credentials);
  }

  /**
   * Get the current record of your bank account.
   *
   * @return The parsed response from the Neopets bank page.
   */
  @Override
  public GetBankRecordResult getBankRecord() {
    NeopetsRequest request = new NeopetsRequest(NeopetsURL.BANK);
    return invoke(request, new GetBankRecordResultUnmarshaller());
  }

  /**
   * Deposit Neopoints to your bank account.
   *
   * @param neopoints
   *         The amount of Neopoints to deposit.
   *
   * @throws InvalidNeopointsAmountException
   *         If the amount requested exceeds the amount of neopoints on hand.
   */
  @Override
  public void depositNeopoints(int neopoints) throws InvalidNeopointsAmountException {
    depositNeopoints(new DepositNeopointsRequest(neopoints));
  }

  /**
   * Deposit Neopoints to your bank account.
   *
   * @param depositNeopointsRequest
   *         Container for the required parameters to execute the request.
   *
   * @throws InvalidNeopointsAmountException
   *         If the amount requested exceeds the amount of neopoints on hand.
   */
  public void depositNeopoints(DepositNeopointsRequest depositNeopointsRequest) throws InvalidNeopointsAmountException {
    NeopetsRequest request = new DepositNeopointsRequestMarshaller().marshall(depositNeopointsRequest);
    invoke(request, new ErrorUnmarshaller())
            .handle(new InvalidNeopointsAmountExceptionHandler());
  }

  /**
   * Withdraw Neopoints from your bank account.
   *
   * @param neopoints
   *         The amount of Neopoints to withdraw.
   *
   * @throws InvalidNeopointsAmountException
   *         If the amount requested exceeds the current balance of your bank account.
   * @throws WithdrawalLimitException
   *         If the number of withdraws already made today exceeds the daily limit of 15.
   */
  @Override
  public void withdrawNeopoints(int neopoints) throws InvalidNeopointsAmountException, WithdrawalLimitException {
    withdrawNeopoints(new WithdrawNeopointsRequest(neopoints));
  }

  /**
   * Withdraw Neopoints from your bank account.
   *
   * @param withdrawNeopointsRequest
   *         Container for the required parameters to execute the request.
   *
   * @throws InvalidNeopointsAmountException
   *         If the amount requested exceeds the current balance of your bank account.
   * @throws WithdrawalLimitException
   *         If the number of withdraws already made today exceeds the daily limit of 15.
   */
  public void withdrawNeopoints(WithdrawNeopointsRequest withdrawNeopointsRequest)
          throws InvalidNeopointsAmountException, WithdrawalLimitException {
    NeopetsRequest request = new WithdrawNeopointsRequestMarshaller().marshall(withdrawNeopointsRequest);
    invoke(request, new ErrorUnmarshaller())
            .handle(new InvalidNeopointsAmountExceptionHandler())
            .handle(new WithdrawalLimitExceptionHandler());
  }

  /**
   * Collect the daily interest from your bank account.
   *
   * @throws AlreadyClaimedInterestException
   *         If the interest was already claimed today.
   */
  @Override
  public void collectInterest() throws AlreadyClaimedInterestException {
    collectInterest(new CollectInterestRequest());
  }

  /**
   * Collect the daily interest from your bank account.
   *
   * @param collectInterestRequest
   *         Container for the required parameters to execute the request.
   *
   * @throws AlreadyClaimedInterestException
   *         If the interest was already claimed today.
   */
  public void collectInterest(CollectInterestRequest collectInterestRequest) throws AlreadyClaimedInterestException {
    NeopetsRequest request = new CollectInterestRequestMarshaller().marshall(collectInterestRequest);
    invoke(request, new ErrorUnmarshaller())
            .handle(new AlreadyClaimedInterestExceptionHandler());
  }

}

