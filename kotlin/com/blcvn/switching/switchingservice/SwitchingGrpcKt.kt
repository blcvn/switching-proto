package com.blcvn.switching.switchingservice

import com.blcvn.switching.switchingservice.SwitchingServiceGrpc.getServiceDescriptor
import io.grpc.CallOptions
import io.grpc.CallOptions.DEFAULT
import io.grpc.Channel
import io.grpc.Metadata
import io.grpc.MethodDescriptor
import io.grpc.ServerServiceDefinition
import io.grpc.ServerServiceDefinition.builder
import io.grpc.ServiceDescriptor
import io.grpc.Status.UNIMPLEMENTED
import io.grpc.StatusException
import io.grpc.kotlin.AbstractCoroutineServerImpl
import io.grpc.kotlin.AbstractCoroutineStub
import io.grpc.kotlin.ClientCalls.unaryRpc
import io.grpc.kotlin.ServerCalls.unaryServerMethodDefinition
import io.grpc.kotlin.StubFor
import kotlin.String
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

/**
 * Holder for Kotlin coroutine-based client and server APIs for
 * switchingservice.v1.SwitchingService.
 */
public object SwitchingServiceGrpcKt {
  public const val SERVICE_NAME: String = SwitchingServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = getServiceDescriptor()

  public val registerBankMethod:
      MethodDescriptor<Switching.RegisterBankRequest, Switching.RegisterBankResponse>
    @JvmStatic
    get() = SwitchingServiceGrpc.getRegisterBankMethod()

  public val queryBankBalanceMethod:
      MethodDescriptor<Switching.QueryBalanceRequest, Switching.QueryBalanceResponse>
    @JvmStatic
    get() = SwitchingServiceGrpc.getQueryBankBalanceMethod()

  public val queryTokenBalanceMethod:
      MethodDescriptor<Switching.QueryBalanceRequest, Switching.QueryBalanceResponse>
    @JvmStatic
    get() = SwitchingServiceGrpc.getQueryTokenBalanceMethod()

  public val holdPaymentMethod:
      MethodDescriptor<Switching.HoldPaymentRequest, Switching.PaymentResponse>
    @JvmStatic
    get() = SwitchingServiceGrpc.getHoldPaymentMethod()

  public val holdBatchPaymentMethod:
      MethodDescriptor<Switching.HoldBatchPaymentRequest, Switching.BatchPaymentResponse>
    @JvmStatic
    get() = SwitchingServiceGrpc.getHoldBatchPaymentMethod()

  public val cancelPaymentMethod:
      MethodDescriptor<Switching.CancelPaymentRequest, Switching.TransactionResponse>
    @JvmStatic
    get() = SwitchingServiceGrpc.getCancelPaymentMethod()

  public val unheldPaymentMethod:
      MethodDescriptor<Switching.UnheldPaymentRequest, Switching.TransactionResponse>
    @JvmStatic
    get() = SwitchingServiceGrpc.getUnheldPaymentMethod()

  public val confirmPaymentMethod:
      MethodDescriptor<Switching.ConfirmPaymentRequest, Switching.PaymentResponse>
    @JvmStatic
    get() = SwitchingServiceGrpc.getConfirmPaymentMethod()

  public val depositBankMethod:
      MethodDescriptor<Switching.BankTransactionRequest, Switching.TransactionResponse>
    @JvmStatic
    get() = SwitchingServiceGrpc.getDepositBankMethod()

  public val withdrawBankMethod:
      MethodDescriptor<Switching.BankTransactionRequest, Switching.TransactionResponse>
    @JvmStatic
    get() = SwitchingServiceGrpc.getWithdrawBankMethod()

  public val mintToBankCodeMethod:
      MethodDescriptor<Switching.BankTransactionRequest, Switching.TransactionResponse>
    @JvmStatic
    get() = SwitchingServiceGrpc.getMintToBankCodeMethod()

  /**
   * A stub for issuing RPCs to a(n) switchingservice.v1.SwitchingService service as suspending
   * coroutines.
   */
  @StubFor(SwitchingServiceGrpc::class)
  public class SwitchingServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<SwitchingServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): SwitchingServiceCoroutineStub =
        SwitchingServiceCoroutineStub(channel, callOptions)

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun registerBank(request: Switching.RegisterBankRequest, headers: Metadata =
        Metadata()): Switching.RegisterBankResponse = unaryRpc(
      channel,
      SwitchingServiceGrpc.getRegisterBankMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun queryBankBalance(request: Switching.QueryBalanceRequest, headers: Metadata =
        Metadata()): Switching.QueryBalanceResponse = unaryRpc(
      channel,
      SwitchingServiceGrpc.getQueryBankBalanceMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun queryTokenBalance(request: Switching.QueryBalanceRequest, headers: Metadata =
        Metadata()): Switching.QueryBalanceResponse = unaryRpc(
      channel,
      SwitchingServiceGrpc.getQueryTokenBalanceMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun holdPayment(request: Switching.HoldPaymentRequest, headers: Metadata =
        Metadata()): Switching.PaymentResponse = unaryRpc(
      channel,
      SwitchingServiceGrpc.getHoldPaymentMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun holdBatchPayment(request: Switching.HoldBatchPaymentRequest,
        headers: Metadata = Metadata()): Switching.BatchPaymentResponse = unaryRpc(
      channel,
      SwitchingServiceGrpc.getHoldBatchPaymentMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun cancelPayment(request: Switching.CancelPaymentRequest, headers: Metadata =
        Metadata()): Switching.TransactionResponse = unaryRpc(
      channel,
      SwitchingServiceGrpc.getCancelPaymentMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun unheldPayment(request: Switching.UnheldPaymentRequest, headers: Metadata =
        Metadata()): Switching.TransactionResponse = unaryRpc(
      channel,
      SwitchingServiceGrpc.getUnheldPaymentMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun confirmPayment(request: Switching.ConfirmPaymentRequest, headers: Metadata =
        Metadata()): Switching.PaymentResponse = unaryRpc(
      channel,
      SwitchingServiceGrpc.getConfirmPaymentMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun depositBank(request: Switching.BankTransactionRequest, headers: Metadata =
        Metadata()): Switching.TransactionResponse = unaryRpc(
      channel,
      SwitchingServiceGrpc.getDepositBankMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun withdrawBank(request: Switching.BankTransactionRequest, headers: Metadata =
        Metadata()): Switching.TransactionResponse = unaryRpc(
      channel,
      SwitchingServiceGrpc.getWithdrawBankMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun mintToBankCode(request: Switching.BankTransactionRequest, headers: Metadata =
        Metadata()): Switching.TransactionResponse = unaryRpc(
      channel,
      SwitchingServiceGrpc.getMintToBankCodeMethod(),
      request,
      callOptions,
      headers
    )
  }

  /**
   * Skeletal implementation of the switchingservice.v1.SwitchingService service based on Kotlin
   * coroutines.
   */
  public abstract class SwitchingServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for switchingservice.v1.SwitchingService.RegisterBank.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun registerBank(request: Switching.RegisterBankRequest):
        Switching.RegisterBankResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method switchingservice.v1.SwitchingService.RegisterBank is unimplemented"))

    /**
     * Returns the response to an RPC for switchingservice.v1.SwitchingService.QueryBankBalance.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun queryBankBalance(request: Switching.QueryBalanceRequest):
        Switching.QueryBalanceResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method switchingservice.v1.SwitchingService.QueryBankBalance is unimplemented"))

    /**
     * Returns the response to an RPC for switchingservice.v1.SwitchingService.QueryTokenBalance.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun queryTokenBalance(request: Switching.QueryBalanceRequest):
        Switching.QueryBalanceResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method switchingservice.v1.SwitchingService.QueryTokenBalance is unimplemented"))

    /**
     * Returns the response to an RPC for switchingservice.v1.SwitchingService.HoldPayment.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun holdPayment(request: Switching.HoldPaymentRequest):
        Switching.PaymentResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method switchingservice.v1.SwitchingService.HoldPayment is unimplemented"))

    /**
     * Returns the response to an RPC for switchingservice.v1.SwitchingService.HoldBatchPayment.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun holdBatchPayment(request: Switching.HoldBatchPaymentRequest):
        Switching.BatchPaymentResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method switchingservice.v1.SwitchingService.HoldBatchPayment is unimplemented"))

    /**
     * Returns the response to an RPC for switchingservice.v1.SwitchingService.CancelPayment.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun cancelPayment(request: Switching.CancelPaymentRequest):
        Switching.TransactionResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method switchingservice.v1.SwitchingService.CancelPayment is unimplemented"))

    /**
     * Returns the response to an RPC for switchingservice.v1.SwitchingService.UnheldPayment.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun unheldPayment(request: Switching.UnheldPaymentRequest):
        Switching.TransactionResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method switchingservice.v1.SwitchingService.UnheldPayment is unimplemented"))

    /**
     * Returns the response to an RPC for switchingservice.v1.SwitchingService.ConfirmPayment.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun confirmPayment(request: Switching.ConfirmPaymentRequest):
        Switching.PaymentResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method switchingservice.v1.SwitchingService.ConfirmPayment is unimplemented"))

    /**
     * Returns the response to an RPC for switchingservice.v1.SwitchingService.DepositBank.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun depositBank(request: Switching.BankTransactionRequest):
        Switching.TransactionResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method switchingservice.v1.SwitchingService.DepositBank is unimplemented"))

    /**
     * Returns the response to an RPC for switchingservice.v1.SwitchingService.WithdrawBank.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun withdrawBank(request: Switching.BankTransactionRequest):
        Switching.TransactionResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method switchingservice.v1.SwitchingService.WithdrawBank is unimplemented"))

    /**
     * Returns the response to an RPC for switchingservice.v1.SwitchingService.MintToBankCode.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun mintToBankCode(request: Switching.BankTransactionRequest):
        Switching.TransactionResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method switchingservice.v1.SwitchingService.MintToBankCode is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = SwitchingServiceGrpc.getRegisterBankMethod(),
      implementation = ::registerBank
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = SwitchingServiceGrpc.getQueryBankBalanceMethod(),
      implementation = ::queryBankBalance
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = SwitchingServiceGrpc.getQueryTokenBalanceMethod(),
      implementation = ::queryTokenBalance
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = SwitchingServiceGrpc.getHoldPaymentMethod(),
      implementation = ::holdPayment
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = SwitchingServiceGrpc.getHoldBatchPaymentMethod(),
      implementation = ::holdBatchPayment
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = SwitchingServiceGrpc.getCancelPaymentMethod(),
      implementation = ::cancelPayment
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = SwitchingServiceGrpc.getUnheldPaymentMethod(),
      implementation = ::unheldPayment
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = SwitchingServiceGrpc.getConfirmPaymentMethod(),
      implementation = ::confirmPayment
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = SwitchingServiceGrpc.getDepositBankMethod(),
      implementation = ::depositBank
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = SwitchingServiceGrpc.getWithdrawBankMethod(),
      implementation = ::withdrawBank
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = SwitchingServiceGrpc.getMintToBankCodeMethod(),
      implementation = ::mintToBankCode
    )).build()
  }
}
