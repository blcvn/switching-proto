package com.blcvn.switching.accesspoint

import com.blcvn.switching.accesspoint.FiServiceGrpc.getServiceDescriptor
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
 * Holder for Kotlin coroutine-based client and server APIs for accesspoint.v1.FiService.
 */
public object FiServiceGrpcKt {
  public const val SERVICE_NAME: String = FiServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = getServiceDescriptor()

  public val createTransferMethod:
      MethodDescriptor<Fi.CreateTransferRequest, Fi.CreateTransferResponse>
    @JvmStatic
    get() = FiServiceGrpc.getCreateTransferMethod()

  public val getTransferMethod: MethodDescriptor<Fi.GetTransferRequest, Common.Transfer>
    @JvmStatic
    get() = FiServiceGrpc.getGetTransferMethod()

  public val getTransferStatusMethod: MethodDescriptor<Fi.GetTransferRequest, Fi.TransferStatus>
    @JvmStatic
    get() = FiServiceGrpc.getGetTransferStatusMethod()

  public val confirmTransferMethod:
      MethodDescriptor<Fi.ConfirmTransferRequest, Fi.ConfirmTransferResponse>
    @JvmStatic
    get() = FiServiceGrpc.getConfirmTransferMethod()

  public val searchTransfersMethod:
      MethodDescriptor<Fi.SearchTransfersRequest, Fi.SearchTransfersResponse>
    @JvmStatic
    get() = FiServiceGrpc.getSearchTransfersMethod()

  public val bulkTransferMethod: MethodDescriptor<Fi.BulkTransferRequest, Fi.BulkTransferResponse>
    @JvmStatic
    get() = FiServiceGrpc.getBulkTransferMethod()

  /**
   * A stub for issuing RPCs to a(n) accesspoint.v1.FiService service as suspending coroutines.
   */
  @StubFor(FiServiceGrpc::class)
  public class FiServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<FiServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): FiServiceCoroutineStub =
        FiServiceCoroutineStub(channel, callOptions)

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
    public suspend fun createTransfer(request: Fi.CreateTransferRequest, headers: Metadata =
        Metadata()): Fi.CreateTransferResponse = unaryRpc(
      channel,
      FiServiceGrpc.getCreateTransferMethod(),
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
    public suspend fun getTransfer(request: Fi.GetTransferRequest, headers: Metadata = Metadata()):
        Common.Transfer = unaryRpc(
      channel,
      FiServiceGrpc.getGetTransferMethod(),
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
    public suspend fun getTransferStatus(request: Fi.GetTransferRequest, headers: Metadata =
        Metadata()): Fi.TransferStatus = unaryRpc(
      channel,
      FiServiceGrpc.getGetTransferStatusMethod(),
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
    public suspend fun confirmTransfer(request: Fi.ConfirmTransferRequest, headers: Metadata =
        Metadata()): Fi.ConfirmTransferResponse = unaryRpc(
      channel,
      FiServiceGrpc.getConfirmTransferMethod(),
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
    public suspend fun searchTransfers(request: Fi.SearchTransfersRequest, headers: Metadata =
        Metadata()): Fi.SearchTransfersResponse = unaryRpc(
      channel,
      FiServiceGrpc.getSearchTransfersMethod(),
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
    public suspend fun bulkTransfer(request: Fi.BulkTransferRequest, headers: Metadata =
        Metadata()): Fi.BulkTransferResponse = unaryRpc(
      channel,
      FiServiceGrpc.getBulkTransferMethod(),
      request,
      callOptions,
      headers
    )
  }

  /**
   * Skeletal implementation of the accesspoint.v1.FiService service based on Kotlin coroutines.
   */
  public abstract class FiServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for accesspoint.v1.FiService.CreateTransfer.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun createTransfer(request: Fi.CreateTransferRequest):
        Fi.CreateTransferResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method accesspoint.v1.FiService.CreateTransfer is unimplemented"))

    /**
     * Returns the response to an RPC for accesspoint.v1.FiService.GetTransfer.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun getTransfer(request: Fi.GetTransferRequest): Common.Transfer = throw
        StatusException(UNIMPLEMENTED.withDescription("Method accesspoint.v1.FiService.GetTransfer is unimplemented"))

    /**
     * Returns the response to an RPC for accesspoint.v1.FiService.GetTransferStatus.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun getTransferStatus(request: Fi.GetTransferRequest): Fi.TransferStatus =
        throw
        StatusException(UNIMPLEMENTED.withDescription("Method accesspoint.v1.FiService.GetTransferStatus is unimplemented"))

    /**
     * Returns the response to an RPC for accesspoint.v1.FiService.ConfirmTransfer.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun confirmTransfer(request: Fi.ConfirmTransferRequest):
        Fi.ConfirmTransferResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method accesspoint.v1.FiService.ConfirmTransfer is unimplemented"))

    /**
     * Returns the response to an RPC for accesspoint.v1.FiService.SearchTransfers.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun searchTransfers(request: Fi.SearchTransfersRequest):
        Fi.SearchTransfersResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method accesspoint.v1.FiService.SearchTransfers is unimplemented"))

    /**
     * Returns the response to an RPC for accesspoint.v1.FiService.BulkTransfer.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun bulkTransfer(request: Fi.BulkTransferRequest): Fi.BulkTransferResponse =
        throw
        StatusException(UNIMPLEMENTED.withDescription("Method accesspoint.v1.FiService.BulkTransfer is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = FiServiceGrpc.getCreateTransferMethod(),
      implementation = ::createTransfer
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = FiServiceGrpc.getGetTransferMethod(),
      implementation = ::getTransfer
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = FiServiceGrpc.getGetTransferStatusMethod(),
      implementation = ::getTransferStatus
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = FiServiceGrpc.getConfirmTransferMethod(),
      implementation = ::confirmTransfer
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = FiServiceGrpc.getSearchTransfersMethod(),
      implementation = ::searchTransfers
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = FiServiceGrpc.getBulkTransferMethod(),
      implementation = ::bulkTransfer
    )).build()
  }
}
