package com.blcvn.switching.onchain

import com.blcvn.switching.onchain.TransactionServiceGrpc.getServiceDescriptor
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
 * Holder for Kotlin coroutine-based client and server APIs for onchain.v1.TransactionService.
 */
public object TransactionServiceGrpcKt {
  public const val SERVICE_NAME: String = TransactionServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = getServiceDescriptor()

  public val creditMethod:
      MethodDescriptor<Transaction.TransactionRequest, Transaction.TransactionResponse>
    @JvmStatic
    get() = TransactionServiceGrpc.getCreditMethod()

  public val debitMethod:
      MethodDescriptor<Transaction.TransactionRequest, Transaction.TransactionResponse>
    @JvmStatic
    get() = TransactionServiceGrpc.getDebitMethod()

  public val transferMethod:
      MethodDescriptor<Transaction.TransactionRequest, Transaction.TransactionResponse>
    @JvmStatic
    get() = TransactionServiceGrpc.getTransferMethod()

  public val getTransactionMethod:
      MethodDescriptor<Transaction.GetTransactionRequest, Transaction.GetTransactionResponse>
    @JvmStatic
    get() = TransactionServiceGrpc.getGetTransactionMethod()

  /**
   * A stub for issuing RPCs to a(n) onchain.v1.TransactionService service as suspending coroutines.
   */
  @StubFor(TransactionServiceGrpc::class)
  public class TransactionServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<TransactionServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): TransactionServiceCoroutineStub
        = TransactionServiceCoroutineStub(channel, callOptions)

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
    public suspend fun credit(request: Transaction.TransactionRequest, headers: Metadata =
        Metadata()): Transaction.TransactionResponse = unaryRpc(
      channel,
      TransactionServiceGrpc.getCreditMethod(),
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
    public suspend fun debit(request: Transaction.TransactionRequest, headers: Metadata =
        Metadata()): Transaction.TransactionResponse = unaryRpc(
      channel,
      TransactionServiceGrpc.getDebitMethod(),
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
    public suspend fun transfer(request: Transaction.TransactionRequest, headers: Metadata =
        Metadata()): Transaction.TransactionResponse = unaryRpc(
      channel,
      TransactionServiceGrpc.getTransferMethod(),
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
    public suspend fun getTransaction(request: Transaction.GetTransactionRequest, headers: Metadata
        = Metadata()): Transaction.GetTransactionResponse = unaryRpc(
      channel,
      TransactionServiceGrpc.getGetTransactionMethod(),
      request,
      callOptions,
      headers
    )
  }

  /**
   * Skeletal implementation of the onchain.v1.TransactionService service based on Kotlin
   * coroutines.
   */
  public abstract class TransactionServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for onchain.v1.TransactionService.Credit.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun credit(request: Transaction.TransactionRequest):
        Transaction.TransactionResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method onchain.v1.TransactionService.Credit is unimplemented"))

    /**
     * Returns the response to an RPC for onchain.v1.TransactionService.Debit.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun debit(request: Transaction.TransactionRequest):
        Transaction.TransactionResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method onchain.v1.TransactionService.Debit is unimplemented"))

    /**
     * Returns the response to an RPC for onchain.v1.TransactionService.Transfer.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun transfer(request: Transaction.TransactionRequest):
        Transaction.TransactionResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method onchain.v1.TransactionService.Transfer is unimplemented"))

    /**
     * Returns the response to an RPC for onchain.v1.TransactionService.GetTransaction.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun getTransaction(request: Transaction.GetTransactionRequest):
        Transaction.GetTransactionResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method onchain.v1.TransactionService.GetTransaction is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TransactionServiceGrpc.getCreditMethod(),
      implementation = ::credit
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TransactionServiceGrpc.getDebitMethod(),
      implementation = ::debit
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TransactionServiceGrpc.getTransferMethod(),
      implementation = ::transfer
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TransactionServiceGrpc.getGetTransactionMethod(),
      implementation = ::getTransaction
    )).build()
  }
}
