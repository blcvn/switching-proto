.PHONY: generate_file
generate_file:
	./scripts/gen-go.sh
	./scripts/gen-java.sh
	./scripts/gen-kotlin.sh
	./scripts/gen-openapi.sh
	./scripts/gen-openapi-md.sh
	./go/scripts/go_mod_tidy_all.sh