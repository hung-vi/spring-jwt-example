#!/bin/bash

set -eu

cd `dirname $0`

## main
echo -e "\n -------------------- \nCreate databases \n --------------------"
echo `docker-compose run --rm postgresql psql -U vnext -h postgresql -f - << EOS
CREATE DATABASE jwtex;
EOS`

echo `docker-compose run --rm -v /Users/hungvi/working/public-git-repositories/spring-jwt-example/scripts/sqls:/tmp/sqls postgresql psql -U vnext jwtex -h postgresql -f /tmp/sqls/db_jwtex_init.sql`
