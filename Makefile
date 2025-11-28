.PHONY: generate_file
generate_file:
	./scripts/gen-go.sh
	./scripts/gen-java.sh
	./scripts/gen-kotlin.sh
	./scripts/gen-openapi.sh
	./scripts/gen-openapi-md.sh
	./go/scripts/go_mod_tidy_all.sh

ifneq ($(filter release_go,$(MAKECMDGOALS)),)
RELEASE_GO_ARGS := $(filter-out release_go,$(MAKECMDGOALS))
RELEASE_GO_MODULE := $(word 1,$(RELEASE_GO_ARGS))
RELEASE_GO_VERSION := $(word 2,$(RELEASE_GO_ARGS))
ifneq ($(RELEASE_GO_ARGS),)
$(RELEASE_GO_ARGS):
	@:
endif
endif

.PHONY: release_go 
release_go:
	@if [ -z "$(RELEASE_GO_MODULE)" ] || [ -z "$(RELEASE_GO_VERSION)" ]; then \
		echo "Usage: make release_go <module> <version>"; \
		echo "Example: make release_go go/switchingservice v1.2.3"; \
		exit 1; \
	fi
	./scripts/release.sh $(RELEASE_GO_MODULE) $(RELEASE_GO_VERSION)