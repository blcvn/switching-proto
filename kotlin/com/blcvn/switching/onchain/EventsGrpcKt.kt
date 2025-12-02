package com.blcvn.switching.onchain

import com.blcvn.switching.onchain.EventsServiceGrpc.getServiceDescriptor
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
import io.grpc.kotlin.ClientCalls.serverStreamingRpc
import io.grpc.kotlin.ClientCalls.unaryRpc
import io.grpc.kotlin.ServerCalls.serverStreamingServerMethodDefinition
import io.grpc.kotlin.ServerCalls.unaryServerMethodDefinition
import io.grpc.kotlin.StubFor
import kotlin.String
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlinx.coroutines.flow.Flow

/**
 * Holder for Kotlin coroutine-based client and server APIs for onchain.v1.EventsService.
 */
public object EventsServiceGrpcKt {
  public const val SERVICE_NAME: String = EventsServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = getServiceDescriptor()

  public val subscribeEventsMethod: MethodDescriptor<Events.SubscribeEventsRequest, Events.Event>
    @JvmStatic
    get() = EventsServiceGrpc.getSubscribeEventsMethod()

  public val registerCallbackMethod:
      MethodDescriptor<Events.SubscribeCallbackRequest, Events.SubscribeCallbackResponse>
    @JvmStatic
    get() = EventsServiceGrpc.getRegisterCallbackMethod()

  public val unregisterCallbackMethod:
      MethodDescriptor<Events.UnsubscribeCallbackRequest, Events.UnsubscribeCallbackResponse>
    @JvmStatic
    get() = EventsServiceGrpc.getUnregisterCallbackMethod()

  /**
   * A stub for issuing RPCs to a(n) onchain.v1.EventsService service as suspending coroutines.
   */
  @StubFor(EventsServiceGrpc::class)
  public class EventsServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<EventsServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): EventsServiceCoroutineStub =
        EventsServiceCoroutineStub(channel, callOptions)

    /**
     * Returns a [Flow] that, when collected, executes this RPC and emits responses from the
     * server as they arrive.  That flow finishes normally if the server closes its response with
     * [`Status.OK`][io.grpc.Status], and fails by throwing a [StatusException] otherwise.  If
     * collecting the flow downstream fails exceptionally (including via cancellation), the RPC
     * is cancelled with that exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return A flow that, when collected, emits the responses from the server.
     */
    public fun subscribeEvents(request: Events.SubscribeEventsRequest, headers: Metadata =
        Metadata()): Flow<Events.Event> = serverStreamingRpc(
      channel,
      EventsServiceGrpc.getSubscribeEventsMethod(),
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
    public suspend fun registerCallback(request: Events.SubscribeCallbackRequest, headers: Metadata
        = Metadata()): Events.SubscribeCallbackResponse = unaryRpc(
      channel,
      EventsServiceGrpc.getRegisterCallbackMethod(),
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
    public suspend fun unregisterCallback(request: Events.UnsubscribeCallbackRequest,
        headers: Metadata = Metadata()): Events.UnsubscribeCallbackResponse = unaryRpc(
      channel,
      EventsServiceGrpc.getUnregisterCallbackMethod(),
      request,
      callOptions,
      headers
    )
  }

  /**
   * Skeletal implementation of the onchain.v1.EventsService service based on Kotlin coroutines.
   */
  public abstract class EventsServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns a [Flow] of responses to an RPC for onchain.v1.EventsService.SubscribeEvents.
     *
     * If creating or collecting the returned flow fails with a [StatusException], the RPC
     * will fail with the corresponding [io.grpc.Status].  If it fails with a
     * [java.util.concurrent.CancellationException], the RPC will fail with status
     * `Status.CANCELLED`.  If creating
     * or collecting the returned flow fails for any other reason, the RPC will fail with
     * `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open fun subscribeEvents(request: Events.SubscribeEventsRequest): Flow<Events.Event> =
        throw
        StatusException(UNIMPLEMENTED.withDescription("Method onchain.v1.EventsService.SubscribeEvents is unimplemented"))

    /**
     * Returns the response to an RPC for onchain.v1.EventsService.RegisterCallback.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun registerCallback(request: Events.SubscribeCallbackRequest):
        Events.SubscribeCallbackResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method onchain.v1.EventsService.RegisterCallback is unimplemented"))

    /**
     * Returns the response to an RPC for onchain.v1.EventsService.UnregisterCallback.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun unregisterCallback(request: Events.UnsubscribeCallbackRequest):
        Events.UnsubscribeCallbackResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method onchain.v1.EventsService.UnregisterCallback is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(serverStreamingServerMethodDefinition(
      context = this.context,
      descriptor = EventsServiceGrpc.getSubscribeEventsMethod(),
      implementation = ::subscribeEvents
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = EventsServiceGrpc.getRegisterCallbackMethod(),
      implementation = ::registerCallback
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = EventsServiceGrpc.getUnregisterCallbackMethod(),
      implementation = ::unregisterCallback
    )).build()
  }
}
