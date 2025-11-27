package com.blcvn.switching.offchain

import com.blcvn.switching.offchain.GpiServiceGrpc.getServiceDescriptor
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
 * Holder for Kotlin coroutine-based client and server APIs for offchain.v1.GpiService.
 */
public object GpiServiceGrpcKt {
  public const val SERVICE_NAME: String = GpiServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = getServiceDescriptor()

  public val createPaymentMethod:
      MethodDescriptor<Gpi.CreatePaymentRequest, Gpi.CreatePaymentResponse>
    @JvmStatic
    get() = GpiServiceGrpc.getCreatePaymentMethod()

  public val getPaymentMethod: MethodDescriptor<Gpi.GetPaymentRequest, Gpi.GetPaymentResponse>
    @JvmStatic
    get() = GpiServiceGrpc.getGetPaymentMethod()

  public val getPaymentStatusMethod:
      MethodDescriptor<Gpi.GetPaymentStatusRequest, Gpi.GetPaymentResponse>
    @JvmStatic
    get() = GpiServiceGrpc.getGetPaymentStatusMethod()

  public val confirmPaymentMethod:
      MethodDescriptor<Gpi.ConfirmPaymentRequest, Gpi.ConfirmPaymentResponse>
    @JvmStatic
    get() = GpiServiceGrpc.getConfirmPaymentMethod()

  /**
   * A stub for issuing RPCs to a(n) offchain.v1.GpiService service as suspending coroutines.
   */
  @StubFor(GpiServiceGrpc::class)
  public class GpiServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<GpiServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): GpiServiceCoroutineStub =
        GpiServiceCoroutineStub(channel, callOptions)

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
    public suspend fun createPayment(request: Gpi.CreatePaymentRequest, headers: Metadata =
        Metadata()): Gpi.CreatePaymentResponse = unaryRpc(
      channel,
      GpiServiceGrpc.getCreatePaymentMethod(),
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
    public suspend fun getPayment(request: Gpi.GetPaymentRequest, headers: Metadata = Metadata()):
        Gpi.GetPaymentResponse = unaryRpc(
      channel,
      GpiServiceGrpc.getGetPaymentMethod(),
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
    public suspend fun getPaymentStatus(request: Gpi.GetPaymentStatusRequest, headers: Metadata =
        Metadata()): Gpi.GetPaymentResponse = unaryRpc(
      channel,
      GpiServiceGrpc.getGetPaymentStatusMethod(),
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
    public suspend fun confirmPayment(request: Gpi.ConfirmPaymentRequest, headers: Metadata =
        Metadata()): Gpi.ConfirmPaymentResponse = unaryRpc(
      channel,
      GpiServiceGrpc.getConfirmPaymentMethod(),
      request,
      callOptions,
      headers
    )
  }

  /**
   * Skeletal implementation of the offchain.v1.GpiService service based on Kotlin coroutines.
   */
  public abstract class GpiServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for offchain.v1.GpiService.CreatePayment.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun createPayment(request: Gpi.CreatePaymentRequest):
        Gpi.CreatePaymentResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method offchain.v1.GpiService.CreatePayment is unimplemented"))

    /**
     * Returns the response to an RPC for offchain.v1.GpiService.GetPayment.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun getPayment(request: Gpi.GetPaymentRequest): Gpi.GetPaymentResponse =
        throw
        StatusException(UNIMPLEMENTED.withDescription("Method offchain.v1.GpiService.GetPayment is unimplemented"))

    /**
     * Returns the response to an RPC for offchain.v1.GpiService.GetPaymentStatus.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun getPaymentStatus(request: Gpi.GetPaymentStatusRequest):
        Gpi.GetPaymentResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method offchain.v1.GpiService.GetPaymentStatus is unimplemented"))

    /**
     * Returns the response to an RPC for offchain.v1.GpiService.ConfirmPayment.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun confirmPayment(request: Gpi.ConfirmPaymentRequest):
        Gpi.ConfirmPaymentResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method offchain.v1.GpiService.ConfirmPayment is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = GpiServiceGrpc.getCreatePaymentMethod(),
      implementation = ::createPayment
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = GpiServiceGrpc.getGetPaymentMethod(),
      implementation = ::getPayment
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = GpiServiceGrpc.getGetPaymentStatusMethod(),
      implementation = ::getPaymentStatus
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = GpiServiceGrpc.getConfirmPaymentMethod(),
      implementation = ::confirmPayment
    )).build()
  }
}
