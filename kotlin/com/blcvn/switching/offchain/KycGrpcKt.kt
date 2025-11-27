package com.blcvn.switching.offchain

import com.blcvn.switching.offchain.KycServiceGrpc.getServiceDescriptor
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
 * Holder for Kotlin coroutine-based client and server APIs for offchain.v1.KycService.
 */
public object KycServiceGrpcKt {
  public const val SERVICE_NAME: String = KycServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = getServiceDescriptor()

  public val listEntitiesMethod: MethodDescriptor<Common.Empty, Kyc.ListEntitiesResponse>
    @JvmStatic
    get() = KycServiceGrpc.getListEntitiesMethod()

  public val getEntityMethod: MethodDescriptor<Kyc.GetEntityRequest, Common.KycEntity>
    @JvmStatic
    get() = KycServiceGrpc.getGetEntityMethod()

  public val getEntityDocumentsMethod:
      MethodDescriptor<Kyc.GetEntityDocsRequest, Kyc.GetEntityDocsResponse>
    @JvmStatic
    get() = KycServiceGrpc.getGetEntityDocumentsMethod()

  public val registerMethod: MethodDescriptor<Kyc.RegisterRequest, Kyc.RegisterResponse>
    @JvmStatic
    get() = KycServiceGrpc.getRegisterMethod()

  public val submitKycMethod: MethodDescriptor<Kyc.SubmitKycRequest, Kyc.SubmitKycResponse>
    @JvmStatic
    get() = KycServiceGrpc.getSubmitKycMethod()

  public val metadataMethod: MethodDescriptor<Common.Empty, Kyc.KycMetadata>
    @JvmStatic
    get() = KycServiceGrpc.getMetadataMethod()

  public val auditMethod: MethodDescriptor<Kyc.GetEntityRequest, Kyc.AuditResponse>
    @JvmStatic
    get() = KycServiceGrpc.getAuditMethod()

  /**
   * A stub for issuing RPCs to a(n) offchain.v1.KycService service as suspending coroutines.
   */
  @StubFor(KycServiceGrpc::class)
  public class KycServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<KycServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): KycServiceCoroutineStub =
        KycServiceCoroutineStub(channel, callOptions)

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
    public suspend fun listEntities(request: Common.Empty, headers: Metadata = Metadata()):
        Kyc.ListEntitiesResponse = unaryRpc(
      channel,
      KycServiceGrpc.getListEntitiesMethod(),
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
    public suspend fun getEntity(request: Kyc.GetEntityRequest, headers: Metadata = Metadata()):
        Common.KycEntity = unaryRpc(
      channel,
      KycServiceGrpc.getGetEntityMethod(),
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
    public suspend fun getEntityDocuments(request: Kyc.GetEntityDocsRequest, headers: Metadata =
        Metadata()): Kyc.GetEntityDocsResponse = unaryRpc(
      channel,
      KycServiceGrpc.getGetEntityDocumentsMethod(),
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
    public suspend fun register(request: Kyc.RegisterRequest, headers: Metadata = Metadata()):
        Kyc.RegisterResponse = unaryRpc(
      channel,
      KycServiceGrpc.getRegisterMethod(),
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
    public suspend fun submitKyc(request: Kyc.SubmitKycRequest, headers: Metadata = Metadata()):
        Kyc.SubmitKycResponse = unaryRpc(
      channel,
      KycServiceGrpc.getSubmitKycMethod(),
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
    public suspend fun metadata(request: Common.Empty, headers: Metadata = Metadata()):
        Kyc.KycMetadata = unaryRpc(
      channel,
      KycServiceGrpc.getMetadataMethod(),
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
    public suspend fun audit(request: Kyc.GetEntityRequest, headers: Metadata = Metadata()):
        Kyc.AuditResponse = unaryRpc(
      channel,
      KycServiceGrpc.getAuditMethod(),
      request,
      callOptions,
      headers
    )
  }

  /**
   * Skeletal implementation of the offchain.v1.KycService service based on Kotlin coroutines.
   */
  public abstract class KycServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for offchain.v1.KycService.ListEntities.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun listEntities(request: Common.Empty): Kyc.ListEntitiesResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method offchain.v1.KycService.ListEntities is unimplemented"))

    /**
     * Returns the response to an RPC for offchain.v1.KycService.GetEntity.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun getEntity(request: Kyc.GetEntityRequest): Common.KycEntity = throw
        StatusException(UNIMPLEMENTED.withDescription("Method offchain.v1.KycService.GetEntity is unimplemented"))

    /**
     * Returns the response to an RPC for offchain.v1.KycService.GetEntityDocuments.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun getEntityDocuments(request: Kyc.GetEntityDocsRequest):
        Kyc.GetEntityDocsResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method offchain.v1.KycService.GetEntityDocuments is unimplemented"))

    /**
     * Returns the response to an RPC for offchain.v1.KycService.Register.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun register(request: Kyc.RegisterRequest): Kyc.RegisterResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method offchain.v1.KycService.Register is unimplemented"))

    /**
     * Returns the response to an RPC for offchain.v1.KycService.SubmitKyc.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun submitKyc(request: Kyc.SubmitKycRequest): Kyc.SubmitKycResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method offchain.v1.KycService.SubmitKyc is unimplemented"))

    /**
     * Returns the response to an RPC for offchain.v1.KycService.Metadata.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun metadata(request: Common.Empty): Kyc.KycMetadata = throw
        StatusException(UNIMPLEMENTED.withDescription("Method offchain.v1.KycService.Metadata is unimplemented"))

    /**
     * Returns the response to an RPC for offchain.v1.KycService.Audit.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun audit(request: Kyc.GetEntityRequest): Kyc.AuditResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method offchain.v1.KycService.Audit is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = KycServiceGrpc.getListEntitiesMethod(),
      implementation = ::listEntities
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = KycServiceGrpc.getGetEntityMethod(),
      implementation = ::getEntity
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = KycServiceGrpc.getGetEntityDocumentsMethod(),
      implementation = ::getEntityDocuments
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = KycServiceGrpc.getRegisterMethod(),
      implementation = ::register
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = KycServiceGrpc.getSubmitKycMethod(),
      implementation = ::submitKyc
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = KycServiceGrpc.getMetadataMethod(),
      implementation = ::metadata
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = KycServiceGrpc.getAuditMethod(),
      implementation = ::audit
    )).build()
  }
}
